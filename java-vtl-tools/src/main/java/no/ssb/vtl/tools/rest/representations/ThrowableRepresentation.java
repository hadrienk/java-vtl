package no.ssb.vtl.tools.rest.representations;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Json representation of a {@link java.lang.Throwable}.
 */
public class ThrowableRepresentation {

    @JsonIgnore
    private Throwable t;

    public ThrowableRepresentation(Throwable t) {
        this.t = t;
    }

    public String getMessage() {
        return t.getMessage();
    }

    public String getStackTrace() {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
