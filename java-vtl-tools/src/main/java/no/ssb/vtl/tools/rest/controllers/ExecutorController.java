package no.ssb.vtl.tools.rest.controllers;

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

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.VTLScriptEngine;
import no.ssb.vtl.tools.rest.representations.DatasetRepresentation;
import no.ssb.vtl.tools.rest.representations.ExecutionRepresentation;
import no.ssb.vtl.tools.rest.representations.ResultRepresentation;
import no.ssb.vtl.tools.rest.representations.ThrowableRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.script.Bindings;
import javax.script.ScriptException;
import java.util.Map;

/**
 * Simple execution service that allows executions of a VTL expression
 * and returns the context.
 */
@CrossOrigin(origins = "http://localhost:8000/")
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.ALL_VALUE
)
@RestController
public class ExecutorController {

    @Autowired
    public VTLScriptEngine vtlEngine;

    @Autowired
    @Qualifier("vtlBindings")
    public Bindings bindings;

    @ExceptionHandler
    @ResponseStatus()
    public Object handleError(Throwable t) {
        return new ThrowableRepresentation(t);
    }

    @RequestMapping(
            path = "/execute2",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object execute(@RequestBody ExecutionRepresentation execution) throws ScriptException {

        Bindings bindings = vtlEngine.createBindings();

        for (DatasetRepresentation dataset : execution.getDatasets()) {
            String name = dataset.getName();
            bindings.put(name, DatasetRepresentation.convertToDataset(dataset));
        }
        vtlEngine.eval(execution.getExpression(), bindings);

        ResultRepresentation datasets = new ResultRepresentation();
        for (Map.Entry<String, Object> entry : bindings.entrySet()) {
            datasets.getDatasets().add(
                    DatasetRepresentation.create(entry.getKey(), (Dataset) entry.getValue())
            );
        }
        return datasets;
    }


}
