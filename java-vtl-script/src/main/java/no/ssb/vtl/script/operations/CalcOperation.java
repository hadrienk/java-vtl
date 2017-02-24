package no.ssb.vtl.script.operations;

import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.function.Function;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;
import static java.lang.String.*;

public class CalcOperation extends AbstractUnaryDatasetOperation {
    
    private Function<DataPoint, Object> componentExpression;
    private final String variableName;
    
    public CalcOperation(Dataset dataset, Function<DataPoint, Object> componentExpression, String identifier) {
        super(checkNotNull(dataset, "the dataset was null"));
        this.componentExpression = checkNotNull(componentExpression, "the function was null");
        
        /*
            TODO: Handle explicit and implicit component computation.
            Need to parse the role
            If implicit, error if already defined.
         */
        variableName = removeQuoteIfNeeded(identifier);
//        return new Dataset() {
//
//
//
//            @Override
//            public String toString() {
//                MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Calc");
//                return helper.omitNullValues().toString();
//            }
//        };
    }
    
    @Override
    protected DataStructure computeDataStructure() {
        Component.Role role = Component.Role.MEASURE;
        Class<?> type = Number.class;
    
        DataStructure.Builder structureCopy = DataStructure.copyOf(getChild().getDataStructure());
        structureCopy.put(variableName, role, type);
        return structureCopy.build();
    }
    
    /**
     * Gets a result.
     * @return a result
     */
    @Override
    public Stream<DataPoint> get() {
        return getChild().get().map(dataPoint -> {
            dataPoint.add(getDataStructure().wrap(variableName, componentExpression.apply(dataPoint)));
            System.out.println(format("Adding value for %s to datapoint %s", variableName, dataPoint));
            return dataPoint;
        });
    }
    
    private static String removeQuoteIfNeeded(String key) {
        if (!key.isEmpty() && key.length() > 3) {
            if (key.charAt(0) == '\'' && key.charAt(key.length() - 1) == '\'') {
                return key.substring(1, key.length() - 1);
            }
        }
        return key;
    }
}
