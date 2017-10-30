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

import com.google.common.collect.Maps;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.VTLScriptEngine;
import no.ssb.vtl.tools.rest.representations.StructureRepresentation;
import no.ssb.vtl.tools.rest.representations.ThrowableRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8000/")
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.ALL_VALUE
)
@RestController
public class UserExecutorController {

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
            path = "/execute",
            method = RequestMethod.POST
    )
    public Collection<String> execute(Reader script) throws IOException, ScriptException {
        vtlEngine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        vtlEngine.eval(script);
        return bindings.keySet();
    }

    @RequestMapping(
            path = "/dataset/{id}/structure",
            method = RequestMethod.GET
    )
    public ResponseEntity<StructureRepresentation> getStructure(@PathVariable String id) {
        Dataset dataset = (Dataset) bindings.get(id);

        if (dataset == null)
            return ResponseEntity.notFound().build();

        StructureRepresentation structureRepresentation = new StructureRepresentation();
        structureRepresentation.setDataStructure(dataset.getDataStructure());
        return ResponseEntity.ok(structureRepresentation);
    }

    @RequestMapping(
            path = "/dataset/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (bindings.containsKey(id)) {
            bindings.remove(id);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(
            path = "/dataset/{id}/data",
            method = RequestMethod.GET
    )
    public Iterable<Map<String, Object>> getData(@PathVariable String id) {
        final Dataset dataset = (Dataset) bindings.get(id);
        DataStructure structure = dataset.getDataStructure();
        return () -> {
            return dataset.getData().map(dataPoints -> {
                Map<String, Object> map = Maps.newLinkedHashMap();
                for (Map.Entry<Component, VTLObject> entry : structure.asMap(dataPoints).entrySet()) {
                    map.put(structure.getName(entry.getKey()), entry.getValue().get());
                }
                return map;
            }).iterator();
        };
    }
}
