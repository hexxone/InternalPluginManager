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

public class BytecodeTransformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BytecodeTransformer.class);

    private static final String BODY_TEMPLATE;

    private byte[] bytecode;

    static {
        BytecodeTransformer.LOGGER.trace("Loading interception layout.");

        BufferedReader reader = null;
        try {
            final URL resource = BytecodeTransformer.class.getClassLoader().getResource("interception.jt");
            reader = new BufferedReader(new InputStreamReader(resource.openStream(), Charset.forName("UTF-8")));

            final StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            BODY_TEMPLATE = builder.toString();
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

    public BytecodeTransformer(final byte[] bytecode) {
        assert bytecode != null : "Bytecode must not be null!";

        this.bytecode = bytecode;
    }

    public void transform() throws TransformException {
        try {
            this.internalTransform();
        } catch (final IOException cause) {
            throw new TransformException("An error occurred whilst transforming the class!", cause);
        } catch (final CannotCompileException cause) {
            throw new TransformException("An error occurred compiling the generated code!", cause);
        }
    }

    private void internalTransform() throws IOException, CannotCompileException, TransformException {
        final ClassPool pool = ClassPool.getDefault();
        final CtClass ctClass = pool.makeClass(new ByteArrayInputStream(this.bytecode));

        BytecodeTransformer.LOGGER.debug("Transforming class {}.", ctClass.getName());

        // Only process if the class is interceptable.
        if (!ctClass.hasAnnotation(Interceptable.class)) {
            BytecodeTransformer.LOGGER.debug("Skipping transformation because the class is not tagged with {}.",
                    Interceptable.class.getName());
        } else {
            for (final CtMethod implMethod : ctClass.getDeclaredMethods()) {
                // Wrap each method.

                final String methodName = implMethod.getName();

                BytecodeTransformer.LOGGER.trace("Processing method {}.", methodName);

                final String implMethodName = ctClass.makeUniqueName(methodName + "$impl");

                implMethod.setName(implMethodName);

                final CtMethod method = CtNewMethod.copy(implMethod, methodName, ctClass, null);
                method.setBody(this.buildCode(implMethod, methodName));
                ctClass.addMethod(method);
            }

            this.bytecode = ctClass.toBytecode();

            BytecodeTransformer.LOGGER.debug("Transformed!");
        }
    }

    private String buildCode(final CtMethod implMethod, final String methodName) throws TransformException {
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

        String result = BytecodeTransformer.BODY_TEMPLATE;
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

    public byte[] getBytecode() {
        return this.bytecode;
    }
}
