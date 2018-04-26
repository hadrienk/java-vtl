package no.ssb.vtl.script.operations.union;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.error.VTLRuntimeException;

import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class DuplicateChecker implements UnaryOperator<DataPoint> {

    private  final Order order;
    private final DataStructure structure;
    private DataPoint last;

    public DuplicateChecker(Order order, DataStructure structure) {
        this.order = checkNotNull(order);
        this.structure = checkNotNull(structure);
    }

    @Override
    public DataPoint apply(DataPoint dataPoint) {
        if (last != null && order.compare(last, dataPoint) == 0)
                throwDuplicateError(dataPoint);
        return last = dataPoint;
    }

    private void throwDuplicateError(DataPoint o) {
        //TODO: define an error code encoding. See VTL User Manuel "Constraints and errors"
        Map<Component, VTLObject> row = structure.asMap(o);
        String rowAsString = row.keySet().stream()
                .map(k -> k.getRole() + ":" + row.get(k))
                .collect(Collectors.joining("\n"));
        throw new VTLRuntimeException(String.format("The resulting dataset from a union contains duplicates. Duplicate row: %s", rowAsString), "VTL-1xxx", o);
    }
}
