package no.ssb.vtl.script.visitors;

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

/*-
 * #%L
 * java-vtl-script
 * %%
 * Copyright (C) 2016 Hadrien Kohl
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
 * #L%
 */

import com.google.common.base.Throwables;
import no.ssb.vtl.connectors.Connector;
import no.ssb.vtl.connectors.ConnectorException;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;

import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * A visitor that handles get and puts VTL operators.
 * <p>
 * It uses the list of dataset connectors and returns the first result.
 */
public class ConnectorVisitor extends VTLBaseVisitor<Dataset> {

    final List<Connector> connectors;

    public ConnectorVisitor(List<Connector> connectors) {
        this.connectors = checkNotNull(connectors, "list of connectors was null");
    }
    
    @Override
    public Dataset visitGetFunction(VTLParser.GetFunctionContext ctx) {
        // TODO: Better way to get the content?
        String identifier = ctx.datasetId().getText();
        identifier = identifier.substring(1, identifier.length() - 1);
        try {
            for (Connector connector : connectors) {
                if (!connector.canHandle(identifier)) {
                    continue;
                }
                return connector.getDataset(identifier);
            }
        } catch (ConnectorException ce) {
            Throwables.propagate(ce);
        }
        return null;
    }
    
    @Override
    public Dataset visitPutFunction(VTLParser.PutFunctionContext ctx) {
        // TODO: Get the identifier and the dataset.
        String identifier = "identifier";
        Dataset dataset = null;
        try {

            for (Connector connector : connectors) {
                if (!connector.canHandle(identifier)) {
                    continue;
                }
                return connector.putDataset(identifier, dataset);
            }
            return super.visitPutFunction(ctx);
        } catch (ConnectorException ce) {
            Throwables.propagate(ce);
        }
        return null;
    }
}
