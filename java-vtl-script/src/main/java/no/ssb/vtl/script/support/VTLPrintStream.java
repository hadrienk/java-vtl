package no.ssb.vtl.script.support;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;

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
                dataset.getDataStructure().values().stream()
                        .map(c ->
                                String.format("%s (%d)",
                                        c.getName(),
                                        c.hashCode()
                                )
                        )
                        .collect(Collectors.toList())
        );
        dataset.get().forEach(tuple -> {
            table.addRow(
                    tuple.stream()
                            .map(DataPoint::get)
                            .map(Object::toString)
                            .collect(Collectors.toList())
            );
        });
        table.showTable(this);
    }

    private void print(DataStructure ds) {

        AsciiTable4j structure = new AsciiTable4j();
        structure.addRow(ds.keySet());
        structure.addRow(ds.values().stream()
                .map(Component::getName).collect(Collectors.toList())
        );
        structure.addRow(ds.values().stream()
                .map(Component::getRole).map(Enum::toString).collect(Collectors.toList())
        );
        structure.addRow(ds.values().stream()
                .map(Component::getType).map(Class::getSimpleName).collect(Collectors.toList())
        );
        structure.addRow(ds.values().stream()
                .map(Component::hashCode)
                .map(i -> Integer.toString(i))
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
