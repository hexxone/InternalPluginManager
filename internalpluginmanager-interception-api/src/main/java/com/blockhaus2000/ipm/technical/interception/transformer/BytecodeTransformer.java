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
package com.blockhaus2000.ipm.technical.interception.transformer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blockhaus2000.ipm.technical.interception.annotation.Interceptable;
import com.blockhaus2000.ipm.technical.interception.exception.TransformException;
import com.blockhaus2000.ipm.technical.interception.exception.TransformRuntimeException;

/**
 * The {@link BytecodeTransformer} is used to transform bytecode given in the
 * constructor in order to enable interceptions at all for the given bytecode.
 *
 * <p>
 * For methods, it places event firing at the start of any method, before any
 * statement, and after all statements (including the return statements). <br>
 * </p>
 * <p>
 * For constructor, it places event firing at the start of any constructor,
 * before any statement but the <code>this</code> or <code>super</code>
 * statement, and after all statements, but before every <code>return</code>
 * statement.
 * </p>
 * <p>
 * For class initializers (static blocks), it places event firing at the start
 * of any constructor, before any statement, and after all statements, but
 * before every <code>return</code> statement.
 * </p>
 *
 * <p>
 * <b> NOTE: Only classes tagged with {@link Interceptable} are tracked by this
 * class file transformer. </b>
 * </p>
 *
 */
public class BytecodeTransformer {
    /**
     * The logger for this class.
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BytecodeTransformer.class);

    /**
     * The base path for templates.
     *
     */
    private static final String RESOURCES = "templates/interception/";
    /**
     * The base path for constructor templates.
     *
     */
    private static final String CONSTRUCTOR_RESOURCES = BytecodeTransformer.RESOURCES + "constructor/";
    /**
     * The base path for method templates.
     *
     */
    private static final String METHOD_RESOURCES = BytecodeTransformer.RESOURCES + "method/";

    /**
     * The resource of the template used as whilst a method invocation.
     *
     */
    private static final String METHOD_INVOCATION_RESOURCE = BytecodeTransformer.METHOD_RESOURCES + "invocation.jt";
    /**
     * The resource of the template used as whilst a method is exiting.
     *
     */
    private static final String METHOD_EXIT_RESOURCE = BytecodeTransformer.METHOD_RESOURCES + "exit.jt";
    /**
     * The resource of the template used as whilst a method with no return (
     * <code>void</code>) is exiting.
     *
     */
    private static final String METHOD_EXIT_VOID_RESOURCE = BytecodeTransformer.METHOD_RESOURCES + "exit_void.jt";

    /**
     * The resource of the template used as whilst a constructor invocation.
     *
     */
    private static final String CONSTRUCTOR_INVOCATION_RESOURCE = BytecodeTransformer.CONSTRUCTOR_RESOURCES + "invocation.jt";

    /**
     * The template used as whilst a method invocation.
     *
     */
    private static final String METHOD_INVOCATION_TEMPLATE;
    /**
     * The template used as whilst a method is exiting.
     *
     */
    private static final String METHOD_EXIT_TEMPLATE;
    /**
     * The template used as whilst a method with no return (<code>void</code>)
     * is exiting.
     *
     */
    private static final String METHOD_EXIT_VOID_TEMPLATE;

    /**
     * The template used as whilst a constructor invocation.
     *
     */
    private static final String CONSTRUCTOR_INVOCATION_TEMPLATE;

    /**
     * The bytecode to transform.
     *
     */
    private byte[] bytecode;

    static {
        BytecodeTransformer.LOGGER.trace("Loading interception resources.");

        try {
            METHOD_INVOCATION_TEMPLATE = BytecodeTransformer.loadResource(BytecodeTransformer.METHOD_INVOCATION_RESOURCE);
            METHOD_EXIT_TEMPLATE = BytecodeTransformer.loadResource(BytecodeTransformer.METHOD_EXIT_RESOURCE);
            METHOD_EXIT_VOID_TEMPLATE = BytecodeTransformer.loadResource(BytecodeTransformer.METHOD_EXIT_VOID_RESOURCE);

            CONSTRUCTOR_INVOCATION_TEMPLATE = BytecodeTransformer
                    .loadResource(BytecodeTransformer.CONSTRUCTOR_INVOCATION_RESOURCE);
        } catch (final IOException cause) {
            throw new TransformRuntimeException("An error occurred whilst reading the interception template!", cause);
        }
    }

    /**
     * Constructor of BytecodeTransformer.
     *
     * @param bytecode
     *            The bytecode to transform.
     */
    public BytecodeTransformer(final byte[] bytecode) {
        assert bytecode != null : "Bytecode must not be null!";

        this.bytecode = bytecode;
    }

    /**
     * Transforms the bytecode to enable interceptions (i.e. inserts the firing
     * of the events).
     *
     * @throws TransformException
     *             If any error occurs whilst transforming the bytecode.
     */
    public void transform() throws TransformException {
        try {
            this.internalTransform();
        } catch (final IOException cause) {
            throw new TransformException("An error occurred whilst transforming the class!", cause);
        } catch (final CannotCompileException cause) {
            throw new TransformException("An error occurred compiling the generated code!", cause);
        }
    }

    /**
     * The internal method for {@link BytecodeTransformer#transform()}.
     *
     * @throws IOException
     *             If any I/O error occurs.
     * @throws CannotCompileException
     *             The the generated code is not compilable.
     * @throws TransformException
     *             If any general error occurs whilst transforming the bytecode.
     */
    private void internalTransform() throws IOException, CannotCompileException, TransformException {
        final ClassPool pool = ClassPool.getDefault();
        final CtClass ctClass = pool.makeClass(new ByteArrayInputStream(this.bytecode));
        this.processClass(ctClass);
    }

    /**
     * Processes the given class and transforms it to enable interceptions.
     *
     * @param ctClass
     *            The class to transform.
     * @param force
     *            Whether to force the processing (so to not to check whether
     *            the annotation {@link Interceptable} is present).
     * @throws IOException
     *             If any I/O error occurs.
     * @throws CannotCompileException
     *             If the generated code is not compilable.
     * @throws TransformException
     *             If any general error occurs whilst transforming the class.
     */
    private void processClass(final CtClass ctClass, final boolean force) throws IOException, CannotCompileException,
    TransformException {
        BytecodeTransformer.LOGGER.debug("Transforming class {}.", ctClass.getName());

        // Only process if the class is interceptable or the processing should
        // be forced.
        if (force || ctClass.hasAnnotation(Interceptable.class)) {
            for (final CtMethod method : ctClass.getDeclaredMethods()) {
                BytecodeTransformer.LOGGER.trace("Processing method {}.", method.getName());

                this.processMethod(method);
            }
            for (final CtConstructor ctor : ctClass.getDeclaredConstructors()) {
                BytecodeTransformer.LOGGER.trace("Processing constructor {}.", ctor.getName());

                this.processConstructor(ctor);
            }

            this.bytecode = ctClass.toBytecode();

            BytecodeTransformer.LOGGER.debug("Transformed!");

            try {
                for (final CtClass subClass : ctClass.getDeclaredClasses()) {
                    try {
                        this.processClass(subClass, true);
                    } catch (final Exception cause) {
                        BytecodeTransformer.LOGGER.error("An error occurred whilst processing nested class " + subClass.getName()
                                + "!", cause);
                    }
                }
            } catch (final NotFoundException cause) {
                BytecodeTransformer.LOGGER.warn("Unable to locate nested class in default class pool!", cause);
            }
        } else {
            BytecodeTransformer.LOGGER.debug("Skipping transformation because the class is not tagged with {}.",
                    Interceptable.class.getName());
        }
    }

    /**
     * Processes the given class and transforms it to enable interceptions.
     *
     * @param ctClass
     *            The class to transform.
     * @throws IOException
     *             If any I/O error occurs.
     * @throws CannotCompileException
     *             If the generated code is not compilable.
     * @throws TransformException
     *             If any general error occurs whilst transforming the class.
     */
    private void processClass(final CtClass ctClass) throws IOException, CannotCompileException, TransformException {
        this.processClass(ctClass, false);
    }

    /**
     * Processes the given method to enable interceptions.
     *
     * <p>
     * This replaces the original method body with the code located in the files
     * {@link BytecodeTransformer#METHOD_INVOCATION_TEMPLATE} and
     * {@link BytecodeTransformer#METHOD_EXIT_TEMPLATE} and
     * {@link BytecodeTransformer#METHOD_EXIT_VOID_TEMPLATE}. and inserts a call
     * to a generated method containing the original code.
     * </p>
     *
     * @param implMethod
     *            The method to transform.
     * @throws CannotCompileException
     *             If generated code is not compilable.
     * @throws TransformException
     *             If any general error occurs whilst transforming the given
     *             method.
     */
    private void processMethod(final CtMethod implMethod) throws CannotCompileException, TransformException {
        final CtClass ctClass = implMethod.getDeclaringClass();

        final String methodName = implMethod.getName();
        final String implMethodName = ctClass.makeUniqueName(methodName + "$impl");

        implMethod.setName(implMethodName);

        final CtMethod method = CtNewMethod.copy(implMethod, methodName, ctClass, null);
        method.setBody(this.generateMethodBody(implMethod, methodName));
        ctClass.addMethod(method);
    }

    /**
     * Processes the given constructor to enable interceptions.
     *
     * <p>
     * This replaces the original constructor body with the code located in the
     * file {@link BytecodeTransformer#CONSTRUCTOR_INVOCATION_TEMPLATE}.
     * </p>
     *
     * @param ctor
     *            The constructor to transform.
     * @throws CannotCompileException
     *             If generated code is not compilable.
     * @throws TransformException
     *             If any general error occurs whilst transforming the given
     *             method.
     */
    private void processConstructor(final CtConstructor ctor) throws CannotCompileException, TransformException {
        ctor.setBody(this.generateConstructorBody(ctor));
    }

    /**
     * Generates the method body for the given method (i.e. the code that is
     * inserted into the method body later on). The generated code is mainly
     * placed in the files
     * {@link BytecodeTransformer#METHOD_INVOCATION_TEMPLATE} and
     * {@link BytecodeTransformer#METHOD_EXIT_TEMPLATE} and
     * {@link BytecodeTransformer#METHOD_EXIT_VOID_TEMPLATE}.
     *
     * @param implMethod
     *            The method which implements the original code (i.e. a method
     *            with a generated name).
     * @param methodName
     *            The real method name (i.e. the name of the method which will
     *            contain the generated code).
     * @return The generated code.
     * @throws TransformException
     *             If any general transformation error occurs.
     */
    private String generateMethodBody(final CtMethod implMethod, final String methodName) throws TransformException {
        final String className = implMethod.getDeclaringClass().getName();
        final String realMethodName = implMethod.getName();
        final String returnType;
        final String parameters;
        try {
            final String tmpReturnType = implMethod.getReturnType().getName();
            returnType = tmpReturnType == null || tmpReturnType.equals("void") ? null : tmpReturnType;

            parameters = this.extractParameters(implMethod.getParameterTypes(), Modifier.isStatic(implMethod.getModifiers()));
        } catch (final NotFoundException cause) {
            throw new TransformException("Could not find the return type nor the parameter types!", cause);
        }

        final String template;
        if (returnType == null) {
            template = BytecodeTransformer.METHOD_INVOCATION_TEMPLATE + BytecodeTransformer.METHOD_EXIT_VOID_TEMPLATE;
        } else {
            template = BytecodeTransformer.METHOD_INVOCATION_TEMPLATE + BytecodeTransformer.METHOD_EXIT_TEMPLATE;
        }
        return this.prepareTemplate(template, className, realMethodName, returnType, methodName, parameters);
    }

    /**
     * Generates the constructor body for the given constructor (i.e. the code
     * that is inserted into the constructor body later on). The generated code
     * is mainly placed in the file
     * {@link BytecodeTransformer#CONSTRUCTOR_INVOCATION_TEMPLATE}.
     *
     * @param ctor
     *            The constructor to generate the body for.
     * @return The generated code.
     * @throws TransformException
     *             If any general transformation error occurs.
     */
    private String generateConstructorBody(final CtConstructor ctor) throws TransformException {
        final String className = ctor.getDeclaringClass().getName();
        final String parameters;
        try {
            parameters = this.extractParameters(ctor.getParameterTypes(), false);
        } catch (final NotFoundException cause) {
            throw new TransformException("Could not find the parameter types!", cause);
        }

        return this.prepareTemplate(BytecodeTransformer.CONSTRUCTOR_INVOCATION_TEMPLATE, className, null, null, null, parameters);
    }

    /**
     * Prepares the given template for usage in a method or constructor body (it
     * replaces the placeholder). If any of the given values is
     * <code>null</code>, it will not be processed.
     *
     * @param template
     *            The template to prepare.
     * @param className
     *            The class name. Replaces <code>%{CLASS_NAME}</code>.
     * @param realMethodName
     *            The real method name. Replaces
     *            <code>%{REAL_METHOD_NAME}</code>.
     * @param returnType
     *            The return type. Replaces <code>%{RETURN_TYPE}</code>.
     * @param methodName
     *            The method name. Replaces <code>%{METHOD_NAME}</code>.
     * @param parameters
     *            The parameters. Replaces <code>%{PARAMETERS}</code>.
     * @return The prepared templates.
     */
    private String prepareTemplate(final String template, final String className, final String realMethodName,
            final String returnType, final String methodName, final String parameters) {
        String result = template;
        if (className != null) {
            result = result.replace("%{CLASS_NAME}", className);
        }
        if (realMethodName != null) {
            result = result.replace("%{REAL_METHOD_NAME}", realMethodName);
        }
        if (returnType != null) {
            result = result.replace("%{RETURN_TYPE}", returnType);
        }
        if (methodName != null) {
            result = result.replace("%{METHOD_NAME}", methodName);
        }
        if (parameters != null) {
            result = result.replace("%{PARAMETERS}", parameters);
        }
        return result;
    }

    /**
     * Generates code that can be read by Javassist as regular variables. It
     * includes every parameter as a object in an array-like code.
     *
     * @param parameterTypes
     *            The parameter types to generate the code for.
     * @param isStatic
     *            Whether the method (or the constructor) is used in a static
     *            environment.
     * @return The generated code.
     */
    private String extractParameters(final CtClass[] parameterTypes, final boolean isStatic) {
        final int paramCount = parameterTypes.length;
        final StringBuilder paramsBuilder = new StringBuilder("null");
        for (int i = 0; i < paramCount; i++) {
            if (isStatic || i > 0) {
                paramsBuilder.append(", com.blockhaus2000.ipm.base.ObjectUtil.toObject($");
                paramsBuilder.append(i);
                paramsBuilder.append(")");
            }
        }
        return paramsBuilder.toString();
    }

    /**
     * Loads a text file with the given name from the resources of the current
     * class loader.
     *
     * @param resourceName
     *            The name of the resource to load.
     * @return The loaded resource.
     * @throws IOException
     *             If an I/O error occurs.
     */
    private static String loadResource(final String resourceName) throws IOException {
        BufferedReader reader = null;
        try {
            final URL resource = BytecodeTransformer.class.getClassLoader().getResource(resourceName);
            reader = new BufferedReader(new InputStreamReader(resource.openStream()));

            String data = "";
            String line;
            while ((line = reader.readLine()) != null) {
                data += "\n" + line;
            }
            return data;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * Return the stored bytecode.
     *
     * <p>
     * If the bytecode was transformed using the
     * {@link BytecodeTransformer#transform()} method, this will urn the
     * transformed bytecode.
     * </p>
     *
     * @return {@link BytecodeTransformer#bytecode}
     */
    public byte[] getBytecode() {
        return this.bytecode;
    }
}
