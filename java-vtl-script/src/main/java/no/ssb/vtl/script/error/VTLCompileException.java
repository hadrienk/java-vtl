package no.ssb.vtl.script.error;

/*
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import com.google.common.collect.Lists;

import javax.script.ScriptException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class VTLCompileException extends ScriptException {

    public List<VTLScriptException> getErrors() {
        return errors;
    }

    private List<VTLScriptException> errors = Lists.newArrayList();

    public VTLCompileException(List<VTLScriptException> errors) {
        super("compilation errors");
        this.errors = checkNotNull(errors);
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder(super.getMessage());
        for (VTLScriptException exception : getErrors()) {
            message.append("\n\t - ").append(exception.getMessage());
        }
        return message.toString();
    }
}
