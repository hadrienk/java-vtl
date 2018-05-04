package no.ssb.vtl.script.support;

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

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A {@link PrintStream} that can print vtl objects
 */
public class VTLPrintStream extends PrintStream {

    public VTLPrintStream(OutputStream out) {
        super(out);
    }

    public VTLPrintStream(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public VTLPrintStream(OutputStream out, boolean autoFlush, String encoding) throws UnsupportedEncodingException {
        super(out, autoFlush, encoding);
    }

    private void print(Dataset dataset) {

        AsciiTable4j table = new AsciiTable4j();
        table.addRow(
                dataset.getDataStructure().entrySet().stream()
                        .map(c ->
                                String.format("%s (%d)",
                                        c.getKey(),
                                        c.getValue().hashCode()
                                )
                        )
                        .collect(Collectors.toList())
        );
        try (Stream<DataPoint> data = dataset.getData()) {
            data.forEach(tuple -> {
                table.addRow(
                        tuple.stream()
                                .map(VTLObject::get)
                                .map(o -> o == null ? "[NULL]" : o)
                                .map(Object::toString)
                                .collect(Collectors.toList())
                );
            });
        }
        table.showTable(this);
    }

    private void print(DataStructure ds) {

        AsciiTable4j structure = new AsciiTable4j();
        structure.addRow(ds.keySet());
        structure.addRow(ds.values().stream()
                .map(Component::getRole).map(Enum::toString).collect(Collectors.toList())
        );
        structure.addRow(ds.values().stream()
                .map(Component::getType).map(Class::getSimpleName).collect(Collectors.toList())
        );
        structure.addRow(ds.values().stream()
                .map(Component::hashCode)
                .map(Long::toString)
                .collect(Collectors.toList())
        );
        structure.showTable(this);
    }

    @Override
    public void println(Object x) {
        print(x);
        super.println();
    }

    @Override
    public void print(Object obj) {
        if (obj instanceof DataStructure) {
            print((DataStructure) obj);
        } else if (obj instanceof Dataset) {
            print((Dataset) obj);
        } else {
            super.print(obj);
        }
    }
}
