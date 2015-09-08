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
import java.nio.charset.Charset;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
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
     * The name of the resource file containing the template for method bodies.
     *
     */
    private static final String METHOD_BODY_RESOURCE = "method_body.jt";

    /**
     * The method body template, loaded in the static block.
     *
     */
    private static final String METHOD_BODY_TEMPLATE;

    /**
     * The bytecode to transform.
     *
     */
    private byte[] bytecode;

    static {
        BytecodeTransformer.LOGGER.trace("Loading interception layout.");

        BufferedReader reader = null;
        try {
            final URL resource = BytecodeTransformer.class.getClassLoader().getResource(BytecodeTransformer.METHOD_BODY_RESOURCE);
            reader = new BufferedReader(new InputStreamReader(resource.openStream(), Charset.forName("UTF-8")));

            final StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            METHOD_BODY_TEMPLATE = builder.toString();
        } catch (final IOException cause) {
            throw new TransformRuntimeException("An error occurred whilst reading the interception template!", cause);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException cause) {
                    throw new TransformRuntimeException("An error occurred whilst closing the interception template stream!",
                            cause);
                }
            }
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
     * @throws IOException
     *             If any I/O error occurs.
     * @throws CannotCompileException
     *             If the generated code is not compilable.
     * @throws TransformException
     *             If any general error occurs whilst transforming the class.
     */
    private void processClass(final CtClass ctClass) throws IOException, CannotCompileException, TransformException {
        BytecodeTransformer.LOGGER.debug("Transforming class {}.", ctClass.getName());

        // Only process if the class is interceptable.
        if (!ctClass.hasAnnotation(Interceptable.class)) {
            BytecodeTransformer.LOGGER.debug("Skipping transformation because the class is not tagged with {}.",
                    Interceptable.class.getName());
        } else {
            for (final CtMethod method : ctClass.getDeclaredMethods()) {
                BytecodeTransformer.LOGGER.trace("Processing method {}.", method.getName());

                this.processMethod(method);
            }

            this.bytecode = ctClass.toBytecode();

            BytecodeTransformer.LOGGER.debug("Transformed!");
        }

        try {
            for (final CtClass subClass : ctClass.getDeclaredClasses()) {
                try {
                    this.processClass(subClass);
                } catch (final Exception cause) {
                    BytecodeTransformer.LOGGER.error("An error occurred whilst processing nested class " + subClass.getName()
                            + "!", cause);
                }
            }
        } catch (final NotFoundException cause) {
            BytecodeTransformer.LOGGER.warn("Unable to locate nested class in default class pool!", cause);
        }
    }

    /**
     * Processes the given method to enable interceptions.
     *
     * <p>
     * This replaces the original method body with the code located in the file
     * {@link BytecodeTransformer#METHOD_BODY_RESOURCE} and inserts a call to a
     * generated method containing the original code.
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
     * Generates the method body for the given method (i.e. the code that is
     * inserted into the method body later on). The generated code is mainly
     * placed in the file {@link BytecodeTransformer#METHOD_BODY_RESOURCE}.
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
        final boolean isStatic = Modifier.isStatic(implMethod.getModifiers());
        final String returnType;
        final boolean hasReturn;
        final int paramCount;
        try {
            returnType = implMethod.getReturnType().getName();
            hasReturn = !returnType.equals("void");
            paramCount = implMethod.getParameterTypes().length;
        } catch (final NotFoundException cause) {
            throw new TransformException("Could not find the return type!", cause);
        }

        String result = BytecodeTransformer.METHOD_BODY_TEMPLATE;
        result = result.replace("%{CLASS_NAME}", implMethod.getDeclaringClass().getName());
        result = result.replace("%{REAL_METHOD_NAME}", methodName);
        result = result.replace("%{HAS_RETURN}", String.valueOf(hasReturn));
        result = result.replace("%{RETURN_TYPE}", returnType);
        result = result.replace("%{METHOD_NAME}", implMethod.getName());
        final StringBuilder params = new StringBuilder("null");
        for (int i = 0; i < paramCount; i++) {
            if (isStatic || i > 0) {
                params.append(", com.blockhaus2000.ipm.base.ObjectUtil.toObject($");
                params.append(i);
                params.append(")");
            }
        }
        result = result.replace("%{PARAMETERS}", params.toString());
        result = result.replace("&{CNR}", hasReturn ? "" : "//");
        return result;
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
