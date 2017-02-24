package no.ssb.vtl.script.operations;

import com.google.common.collect.Maps;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;

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

public class CheckSingleRuleOperation extends AbstractUnaryDatasetOperation {

    public enum RowsToReturn {
        NOT_VALID,
        VALID,
        ALL
    }

    public enum ComponentsToReturn {
        MEASURES,
        CONDITION
    }

    private final RowsToReturn rowsToReturn;
    private final ComponentsToReturn componentsToReturn;
    private final String errorCode;
    private final Integer errorLevel;

    private CheckSingleRuleOperation(Builder builder) {
        super(checkNotNull(builder.dataset, "dataset was null"));
        this.rowsToReturn = builder.rowsToReturn;
        this.componentsToReturn = builder.componentsToReturn;
        this.errorCode = builder.errorCode;
        this.errorLevel = builder.errorLevel;

        checkArgument(!(this.componentsToReturn == ComponentsToReturn.MEASURES && this.rowsToReturn == RowsToReturn.ALL),
                "cannot use 'all' with 'measures' parameter");

        checkDataStructure(getChild());
    }

    private void checkDataStructure(Dataset dataset) {
        int noIdentifiers = Maps.filterValues(dataset.getDataStructure().getRoles(), role -> role == Component.Role.IDENTIFIER)
                .size();
        checkArgument(noIdentifiers > 0, "dataset does not have identifier components");
    }

    @Override
    public Stream<? extends DataPoint> getData() {
        Stream<DataPoint> tupleStream = getChild().get();

        //first calculate the new data points...
        if (componentsToReturn == ComponentsToReturn.MEASURES) {
            tupleStream = tupleStream.map(dataPoints -> {
                List<VTLObject> dataPointsNewList = new ArrayList<>(dataPoints);
                dataPointsNewList.add(getErrorCodeAsDataPoint());
                if (errorLevel != null) {
                    dataPointsNewList.add(getErrorCodeAsDataPoint());
                }
                return DataPoint.create(dataPointsNewList);
            });
        } else if (componentsToReturn == ComponentsToReturn.CONDITION) {
            tupleStream = tupleStream.map(dataPoints -> {
                List<VTLObject> dataPointsNewList = new ArrayList<>(dataPoints);
                dataPointsNewList.add(new VTLObject(getDataStructure().get("CONDITION")) {
                    @Override
                    public Object get() {
                        return dataPoints.stream()
                                .filter(dp -> dp.getRole() == Component.Role.MEASURE && dp.getType().equals(Boolean.class))
                                .map(VTLObject::get)
                                .reduce(true, (a, b) -> Boolean.logicalAnd((Boolean)a, (Boolean)b));
                    }
                });
                dataPointsNewList.add(getErrorCodeAsDataPoint());
                if (errorLevel != null) {
                    dataPointsNewList.add(getErrorLevelAsDataPoint());
                }
                return DataPoint.create(dataPointsNewList);
            });
        }

        //... then filter rows
        if (rowsToReturn == RowsToReturn.NOT_VALID) {
                        tupleStream = tupleStream.filter(dataPoint ->
                    dataPoint.stream().filter(isConditionComponent())
                            .anyMatch(vtlObject -> vtlObject.get().equals(false)));
        } else if (rowsToReturn == RowsToReturn.VALID) {
            tupleStream = tupleStream.filter(dataPoint ->
                    //TODO should an exception be thrown if CONDITION not found?
                    dataPoint.stream().filter(isConditionComponent())
                            .anyMatch(vtlObject -> vtlObject.get().equals(true)));
        } //else if ("all".equals(rowsToReturn)) //all is not filtered

        return tupleStream;
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getSize() {
        return Optional.empty();
    }


    @Override
    protected DataStructure computeDataStructure() {
        Dataset dataset = getChild();
        Map<String, Component.Role> newRoles = new HashMap<>(dataset.getDataStructure().getRoles());
        Map<String, Class<?>> newTypes = new HashMap<>(dataset.getDataStructure().getTypes());
        Set<String> oldNames = dataset.getDataStructure().keySet();

        if (componentsToReturn == ComponentsToReturn.CONDITION) {
            removeAllComponentsButIdentifiersAndBooleanMeasures(newRoles, newTypes, oldNames);
            addComponent("CONDITION", newRoles, newTypes, Component.Role.MEASURE, Boolean.class);
        }

        addComponent("errorcode", newRoles, newTypes, Component.Role.ATTRIBUTE, String.class);

        if (errorLevel != null) {
            addComponent("errorlevel", newRoles, newTypes, Component.Role.ATTRIBUTE, Integer.class);
        }

        BiFunction<Object, Class<?>, ?> converter = dataset.getDataStructure().converter();
        return DataStructure.of(converter, newTypes, newRoles);
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
    @Deprecated
    public Stream<DataPoint> get() {
        return getData().map(o -> o);
    }

    private VTLObject getErrorLevelAsDataPoint() {
        return getDataStructure().wrap("errorlevel", errorLevel);
    }

    private VTLObject getErrorCodeAsDataPoint() {
        return getDataStructure().wrap("errorcode", errorCode);
    }

    private static Predicate<VTLObject> isConditionComponent() {
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
