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

import no.ssb.vtl.model.DataPoint;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;

import static no.ssb.vtl.script.error.VTLErrorCodeUtil.checkVTLCode;

/**
 * Thrown when the VTL execution failed at runtime.
 */
@Deprecated
public class VTLRuntimeException extends RuntimeException implements VTLThrowable {

    private static final long serialVersionUID = 7253953869019949964L;
    private final String VTLCode;
    private final RuleContext context;

    public VTLRuntimeException(String s, String vtlCode, DataPoint dataPoint) {
        super(s);
        this.VTLCode = checkVTLCode(vtlCode, "VTL-1");
        this.context = null;
    }

    public VTLRuntimeException(Exception e, String vtlCode, DataPoint dataPoint) {
        super(e);
        this.VTLCode = checkVTLCode(vtlCode, "VTL-1");
        this.context = null;
    }

    public VTLRuntimeException(String message, String vtlCode, ParserRuleContext context) {
        super(message);
        this.VTLCode = vtlCode;
        this.context = context;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        return message + " at " + context;
    }
    
    @Override
    public String getVTLCode() {
        return VTLCode;
    }
}
