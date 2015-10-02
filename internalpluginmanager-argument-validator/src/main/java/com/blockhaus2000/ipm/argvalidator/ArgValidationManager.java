/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014-2015 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.blockhaus2000.ipm.argvalidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.blockhaus2000.ipm.argvalidator.annotation.AutoCheck;
import com.blockhaus2000.ipm.argvalidator.annotation.rule.FloatRange;
import com.blockhaus2000.ipm.argvalidator.annotation.rule.NonEmpty;
import com.blockhaus2000.ipm.argvalidator.annotation.rule.NonNull;
import com.blockhaus2000.ipm.argvalidator.annotation.rule.Nullable;
import com.blockhaus2000.ipm.argvalidator.annotation.rule.Range;
import com.blockhaus2000.ipm.argvalidator.annotation.rule.Rule;
import com.blockhaus2000.ipm.argvalidator.checker.RuleChecker;
import com.blockhaus2000.ipm.argvalidator.exception.RuleViolationException;
import com.blockhaus2000.ipm.technical.event.EventContext;
import com.blockhaus2000.ipm.technical.event.EventListener;
import com.blockhaus2000.ipm.technical.interception.InterceptionEventManager;
import com.blockhaus2000.ipm.technical.interception.event.AbstractInterceptionEvent;
import com.blockhaus2000.ipm.technical.interception.event.ConstructorInvocationEvent;
import com.blockhaus2000.ipm.technical.interception.event.MethodExitEvent;
import com.blockhaus2000.ipm.technical.interception.event.MethodInvocationEvent;

/**
 * The argument validation manager manages everything relating to the argument
 * validation API.
 *
 */
public final class ArgValidationManager {
    /**
     * THE one and only instance of this class.
     *
     */
    private static final ArgValidationManager INSTANCE = new ArgValidationManager();

    /**
     * Whether the argument validation manager is initialized.
     *
     */
    private boolean initialized;

    /**
     * Constructor of ArgValidationManager.
     *
     */
    private ArgValidationManager() {
        // Nothing to do.
    }

    /**
     * Initializes the argument validation manager.
     *
     * <p>
     * <b> NOTE: THis method can only be called once! </b>
     * </p>
     *
     */
    public void init() {
        if (this.initialized) {
            throw new IllegalStateException("Already initialized!");
        }

        InterceptionEventManager.getInstance().register(this);
    }

    /**
     * Internal listener to check rules on a constructor invocation.
     *
     * <p>
     * <b> NOTE: You must not call this method directly! </b>
     * </p>
     *
     * @param context
     *            The event context.
     */
    @EventListener(ConstructorInvocationEvent.class)
    public void onConstructorInvocation(final EventContext<ConstructorInvocationEvent> context) {
        final ConstructorInvocationEvent event = context.getEvent();
        if (this.isAutoChecked(event)) {
            try {
                this.checkRules(event.getInvokedConstructor().getParameterAnnotations(), event.getParameters());
            } catch (final RuleViolationException cause) {
                event.stopExecution(cause);
            }
        }
    }

    /**
     * Internal listener to check rules on a method invocation.
     *
     * <p>
     * <b> NOTE: You must not call this method directly! </b>
     * </p>
     *
     * @param context
     *            The event context.
     */
    @EventListener(MethodInvocationEvent.class)
    public void onMethodInvocation(final EventContext<MethodInvocationEvent> context) {
        final MethodInvocationEvent event = context.getEvent();
        if (this.isAutoChecked(event)) {
            try {
                this.checkRules(event.getInvokedMethod().getParameterAnnotations(), event.getParameters());
            } catch (final RuleViolationException cause) {
                event.stopExecution(cause);
            }
        }
    }

    /**
     * Internal listener to check rules on a method exit.
     *
     * <p>
     * <b> NOTE: You must not call this method directly! </b>
     * </p>
     *
     * @param context
     *            The event context.
     */
    @EventListener(MethodExitEvent.class)
    public void onMethodExit(final EventContext<MethodExitEvent> context) {
        final MethodExitEvent event = context.getEvent();
        if (this.isAutoChecked(event)) {
            try {
                this.checkRules(event.getInvokedMethod().getAnnotations(), event.getReturnValue());
            } catch (final RuleViolationException cause) {
                event.stopExecution(cause);
            }
        }
    }

    /**
     * Checks every rule that is present in the given annotations for the given
     * object.
     *
     * <p>
     * For example: <code>
     * <pre>
     * foo(\@NonNull \@NonEmpty String str)
     * </pre>
     * </code> In this case, this method is called with
     * <code>annotations = { Non-Null.class-Obj, Non-Empty.class-Obj }</code>
     * and <code>obj = str</code>.
     * </p>
     *
     * @param annotations
     *            The annotations containing the rule annotations that should be
     *            checked.
     * @param obj
     *            The object to apply the rules on. Can be <code>null</code>.
     * @throws RuleViolationException
     *             If any rule is violated.
     */
    private void checkRules(final Annotation[] annotations, final Object obj) throws RuleViolationException {
        for (final Annotation annotation : annotations) {
            if (annotation instanceof Nullable) {
                if (obj == null) {
                    break;
                }
            } else if (annotation instanceof NonNull) {
                if (obj == null) {
                    throw new RuleViolationException(obj + " must not be null!");
                }
            }
            if (obj == null) {
                continue;
            }
            if (annotation instanceof Range) {
                if (obj instanceof Byte || obj instanceof Short || obj instanceof Integer || obj instanceof Long) {
                    final Range range = (Range) annotation;
                    final Long num = (Long) obj;
                    if (num < range.min()) {
                        throw new RuleViolationException(num + " must be >=" + range.min());
                    }
                    if (num > range.max()) {
                        throw new RuleViolationException(num + " must be <=" + range.max());
                    }
                } else {
                    throw new IllegalArgumentException(obj + " has an illegal type (" + obj.getClass() + ") for the rule "
                            + Range.class + "!");
                }
            } else if (annotation instanceof FloatRange) {
                if (obj instanceof Float || obj instanceof Double) {
                    final FloatRange range = (FloatRange) annotation;
                    final Double num = (Double) obj;
                    if (num < range.min()) {
                        throw new RuleViolationException(num + " must be >=" + range.min());
                    }
                    if (num > range.max()) {
                        throw new RuleViolationException(num + " must be <=" + range.max());
                    }
                } else {
                    throw new IllegalArgumentException(obj + " has an illegal type (" + obj.getClass() + ") for the rule "
                            + FloatRange.class + "!");
                }
            } else if (annotation instanceof NonEmpty || annotation instanceof Rule) {
                final Class<? extends RuleChecker> ruleCheckerClass;
                if (annotation instanceof Rule) {
                    ruleCheckerClass = ((Rule) annotation).value();
                } else {
                    ruleCheckerClass = ((NonEmpty) annotation).value();
                }
                final Constructor<? extends RuleChecker> constructor;
                try {
                    constructor = ruleCheckerClass.getConstructor();
                } catch (final NoSuchMethodException cause) {
                    throw new IllegalArgumentException("The rule checker " + ruleCheckerClass.getName()
                            + " has to define a default ctor!", cause);
                }
                final RuleChecker ruleChecker;
                try {
                    ruleChecker = constructor.newInstance();
                } catch (final InstantiationException cause) {
                    throw new IllegalArgumentException("Cannot instantiate the rule checker " + ruleCheckerClass.getName() + "!",
                            cause);
                } catch (final IllegalAccessException cause) {
                    throw new IllegalArgumentException("Cannot instantiate the rule checker " + ruleCheckerClass.getName() + "!",
                            cause);
                } catch (final InvocationTargetException cause) {
                    throw new IllegalArgumentException("Cannot instantiate the rule checker " + ruleCheckerClass.getName() + "!",
                            cause);
                }
                ruleChecker.check(obj);
            }
        }
    }

    /**
     * Checks the rules for multiple object. Invokes
     * {@link ArgValidationManager#checkRules(Annotation[], Object)}.
     *
     * <p>
     * <b> NOTE: <code>annotations.length</code> must be equal to
     * <code>obj.length</code>. </b>
     * </p>
     *
     * @param annotations
     *            The annotations to check.
     * @param objs
     *            The objects to check.
     * @throws RuleViolationException
     *             If any rule is violated.
     */
    private void checkRules(final Annotation[][] annotations, final Object[] objs) throws RuleViolationException {
        for (int i = 0; i < objs.length; i++) {
            this.checkRules(annotations[i], objs[i]);
        }
    }

    /**
     *
     * @param clazz
     *            The {@link Class} to check.
     * @return Whether the given {@link Class} is auto-checked.
     */
    private boolean isAutoChecked(final Class<?> clazz) {
        return clazz.isAnnotationPresent(AutoCheck.class);
    }

    /**
     *
     * @param event
     *            Contains the {@link Class} to check.
     * @return Whether the {@link Class} contained by the given event is
     *         auto-checked.
     */
    private boolean isAutoChecked(final AbstractInterceptionEvent event) {
        return this.isAutoChecked(event.getInvokedClass());
    }

    /**
     *
     * @return {@link ArgValidationManager#INSTANCE}
     */
    public static ArgValidationManager getInstance() {
        return ArgValidationManager.INSTANCE;
    }
}
