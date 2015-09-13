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

public final class ArgValidationManager {
    private static final ArgValidationManager INSTANCE = new ArgValidationManager();

    private boolean initialized;

    /**
     * Constructor of ArgValidationManager.
     *
     */
    private ArgValidationManager() {
        // Nothing to do.
    }

    public void init() {
        if (this.initialized) {
            throw new IllegalStateException("Already initialized!");
        }

        InterceptionEventManager.getInstance().register(this);
    }

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

    private void checkRules(final Annotation[][] annotations, final Object[] objs) throws RuleViolationException {
        for (int i = 0; i < objs.length; i++) {
            this.checkRules(annotations[i], objs[i]);
        }
    }

    private boolean isAutoChecked(final Class<?> clazz) {
        return clazz.isAnnotationPresent(AutoCheck.class);
    }

    private boolean isAutoChecked(final AbstractInterceptionEvent event) {
        return this.isAutoChecked(event.getInvokedClass());
    }

    public static ArgValidationManager getInstance() {
        return ArgValidationManager.INSTANCE;
    }
}
