package no.ssb.vtl.dependencies;

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

public class ComponentRef {
    
    private final String datasetId;
    private final String variableId;

    public ComponentRef(String datasetId, String variableId) {
        this.datasetId = datasetId;
        this.variableId = variableId;
    }
    
    public String getDatasetId() {
        return datasetId;
    }
    
    public String getVariableId() {
        return variableId;
    }
    
    @Override
    public String toString() {
        return String.format("%s.%s", datasetId, variableId);
    }
}
