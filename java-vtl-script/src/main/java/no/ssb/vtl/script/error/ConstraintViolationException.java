package no.ssb.vtl.script.error;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import javax.script.ScriptException;

import static no.ssb.vtl.script.error.VTLErrorCodeUtil.checkVTLCode;

/**
 * Thrown when the VTL received does not respect operators preconditions.
 */
public class ConstraintViolationException extends ScriptException implements VTLThrowable {

    private static final long serialVersionUID = -617398492563000321L;

    private final String VTLCode;

    public ConstraintViolationException(String s, String vtlCode) {
        super(s);
        this.VTLCode = checkVTLCode(vtlCode, "VTL-03");
    }

    public ConstraintViolationException(Exception e, String vtlCode) {
        super(e);
        this.VTLCode = checkVTLCode(vtlCode, "VTL-03");
    }

    public ConstraintViolationException(String message, String fileName, int lineNumber, String vtlCode) {
        super(message, fileName, lineNumber);
        this.VTLCode = checkVTLCode(vtlCode, "VTL-03");
    }

    public ConstraintViolationException(String message, String fileName, int lineNumber, int columnNumber, String vtlCode) {
        super(message, fileName, lineNumber, columnNumber);
        this.VTLCode = checkVTLCode(vtlCode, "VTL-03");
    }

    @Override
    public String getVTLCode() {
        return VTLCode;
    }
}
