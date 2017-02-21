package no.ssb.vtl.script.operations;

import com.google.common.collect.Maps;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class CheckSingleRuleOperation implements Dataset{

    public enum RowsToReturn {
        NOT_VALID,
        VALID,
        ALL
    }

    public enum ComponentsToReturn {
        MEASURES,
        CONDITION
    }

    private final Dataset dataset;
    private final RowsToReturn rowsToReturn;
    private final ComponentsToReturn componentsToReturn;
    private final String errorCode;
    private final Integer errorLevel;
    private DataStructure cache;

    public CheckSingleRuleOperation(Builder builder) {
        this.dataset = checkNotNull(builder.dataset, "dataset was null");
        this.rowsToReturn = builder.rowsToReturn;
        this.componentsToReturn = builder.componentsToReturn;
        this.errorCode = builder.errorCode;
        this.errorLevel = builder.errorLevel;

        checkArgument(!(this.componentsToReturn == ComponentsToReturn.MEASURES && this.rowsToReturn == RowsToReturn.ALL),
                "cannot use 'all' with 'measures' parameter");

        checkDataStructure(this.dataset);
    }

    private void checkDataStructure(Dataset dataset) {
        int noIdentifiers = Maps.filterValues(dataset.getDataStructure().getRoles(), role -> role == Component.Role.IDENTIFIER)
                .size();
        checkArgument(noIdentifiers > 0, "dataset does not have identifier components");
    }

    @Override
    public DataStructure getDataStructure() {
        if (cache == null) {
            Map<String, Component.Role> newRoles = new HashMap<>(dataset.getDataStructure().getRoles());
            Map<String, Class<?>> newTypes = new HashMap<>(dataset.getDataStructure().getTypes());
            Set<String> oldNames = dataset.getDataStructure().keySet();

            if (componentsToReturn == ComponentsToReturn.CONDITION) {
                removeAllComponentsButIdentifiersAndBooleanMeasures(newRoles, newTypes, oldNames);
                addComponent("CONDITION", newRoles, newTypes, Component.Role.MEASURE, Boolean.class);
            }

            addComponent("errorcode", newRoles, newTypes, Component.Role.ATTRIBUTE, String.class);
            addComponent("errorlevel", newRoles, newTypes, Component.Role.ATTRIBUTE, Integer.class);

            BiFunction<Object, Class<?>, ?> converter = dataset.getDataStructure().converter();
            cache = DataStructure.of(converter, newTypes, newRoles);
        }

        return cache;
    }

    private void addComponent(String componentName, Map<String, Component.Role> newRoles,
                              Map<String, Class<?>> newTypes, Component.Role role, Class<?> aClass) {
        newRoles.put(componentName, role);
        newTypes.put(componentName, aClass);
    }

    private void removeAllComponentsButIdentifiersAndBooleanMeasures(Map<String, Component.Role> newRoles,
                                                                     Map<String, Class<?>> newTypes, Set<String> oldNames) {
        for (String oldName : oldNames) {
            if (newRoles.get(oldName) != Component.Role.IDENTIFIER
                    && newRoles.get(oldName) != Component.Role.MEASURE && newTypes.get(oldName) != Boolean.class) {
                newRoles.remove(oldName);
                newTypes.remove(oldName);
            }
        }
    }

    @Override
    public Stream<Tuple> get() {
        Stream<Tuple> tupleStream = dataset.get();

        DataPoint errorCodeDataPoint = getDataStructure().wrap("errorcode", errorCode);
        DataPoint errorLevelDataPoint = getDataStructure().wrap("errorlevel", errorLevel);

        //first calculate the new data points...
        if (componentsToReturn == ComponentsToReturn.MEASURES) {
            tupleStream = tupleStream.map(dataPoints -> {
                        List<DataPoint> dataPointsNewList = new ArrayList<>(dataPoints);
                        dataPointsNewList.add(errorCodeDataPoint);
                        dataPointsNewList.add(errorLevelDataPoint);
                        return Tuple.create(dataPointsNewList);
                    });
        } else if (componentsToReturn == ComponentsToReturn.CONDITION) {
            tupleStream = tupleStream.map(dataPoints -> {
                List<DataPoint> dataPointsNewList = new ArrayList<>(dataPoints);
                dataPointsNewList.add(new DataPoint(getDataStructure().get("CONDITION")) {
                    @Override
                    public Object get() {
                        return dataPoints.values().stream()
                                .filter(dp -> dp.getRole() == Component.Role.MEASURE && dp.getType().equals(Boolean.class))
                                .map(DataPoint::get)
                                .reduce(true, (a, b) -> Boolean.logicalAnd((Boolean)a, (Boolean)b));
                    }
                });
                dataPointsNewList.add(errorCodeDataPoint);
                dataPointsNewList.add(errorLevelDataPoint);
                return Tuple.create(dataPointsNewList);
            });
        }

        //... then filter rows
        if (rowsToReturn == RowsToReturn.NOT_VALID) {
                        tupleStream = tupleStream.filter(tuple ->
                    tuple.values().stream().filter(isConditionComponent())
                            .anyMatch(dataPoint -> dataPoint.get().equals(false)));
        } else if (rowsToReturn == RowsToReturn.VALID) {
            tupleStream = tupleStream.filter(tuple ->
                    //TODO should an exception be thrown if CONDITION not found?
                    tuple.values().stream().filter(isConditionComponent())
                            .anyMatch(dataPoint -> dataPoint.get().equals(true)));
        } //else if ("all".equals(rowsToReturn)) //all is not filtered

        return tupleStream;
    }

    private static Predicate<DataPoint> isConditionComponent() {
        return dataPoint -> dataPoint.getName().equals("CONDITION");
    }

    public static class Builder {

        private Dataset dataset;
        private RowsToReturn rowsToReturn = RowsToReturn.NOT_VALID;
        private ComponentsToReturn componentsToReturn = ComponentsToReturn.MEASURES;
        private String errorCode;
        private Integer errorLevel;

        public Builder(Dataset dataset) {
            this.dataset = dataset;
        }

        public Builder rowsToReturn(RowsToReturn rowsToReturn) {
            this.rowsToReturn = rowsToReturn;
            return this;
        }

        public Builder componentsToReturn(ComponentsToReturn componentsToReturn) {
            this.componentsToReturn = componentsToReturn;
            return this;
        }

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder errorLevel(Integer errorLevel) {
            this.errorLevel = errorLevel;
            return this;
        }

        public CheckSingleRuleOperation build() {
            return new CheckSingleRuleOperation(this);
        }
    }
}
