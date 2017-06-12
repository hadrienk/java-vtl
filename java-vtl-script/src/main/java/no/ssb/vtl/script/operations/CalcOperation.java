package no.ssb.vtl.script.operations;

import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLExpression;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class CalcOperation extends AbstractUnaryDatasetOperation {
    
    private VTLExpression componentExpression;
    private final String variableName;
    
    public CalcOperation(Dataset dataset, VTLExpression componentExpression, String identifier) {
        super(checkNotNull(dataset, "the dataset was null"));
        this.componentExpression = checkNotNull(componentExpression, "the function was null");
        
        /*
            TODO: Handle explicit and implicit component computation.
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

        Boolean implicit = true;
        Component.Role defaultRole = Component.Role.MEASURE;

        DataStructure.Builder builder = DataStructure.builder();
        DataStructure dataStructure = getChild().getDataStructure();
        for (Map.Entry<String, Component> entry : dataStructure.entrySet()) {
            if (dataStructure.containsKey(variableName))
                continue;
            builder.put(entry.getKey(), entry.getValue());
        }

        Component.Role role = Component.Role.MEASURE;
        Class<?> type = componentExpression.getType();

        // TODO: Support implicit
        if (dataStructure.containsKey(variableName) && implicit)
            role = dataStructure.get(variableName).getRole();

        // TODO: Support role change
        if (false)
            role = Component.Role.MEASURE;

        builder.put(variableName, role, type);

        return builder.build();
    }
    
    private static String removeQuoteIfNeeded(String key) {
        if (!key.isEmpty() && key.length() > 3) {
            if (key.charAt(0) == '\'' && key.charAt(key.length() - 1) == '\'') {
                return key.substring(1, key.length() - 1);
            }
        }
        return key;
    }

    @Override
    public Stream<DataPoint> getData() {
        return getChild().getData().map(dataPoint -> {
            dataPoint.add(getDataStructure().wrap(variableName, componentExpression.apply(dataPoint)));
            return dataPoint;
        });
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return getChild().getDistinctValuesCount();
    }

    @Override
    public Optional<Long> getSize() {
        return getChild().getSize();
    }
}
