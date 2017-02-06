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
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class CheckOperation implements Dataset{

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

    public CheckOperation(Dataset dataset, Optional<RowsToReturn> rowsToReturn, Optional<ComponentsToReturn> componentsToReturn,
                          Optional<String> errorCode, Optional<Integer> errorLevel) {
        this.dataset = checkNotNull(dataset, "dataset was null");

        if (rowsToReturn != null && rowsToReturn.isPresent()) {
            this.rowsToReturn = rowsToReturn.get();
        } else {
            this.rowsToReturn = RowsToReturn.NOT_VALID;
        }

        if (componentsToReturn != null && componentsToReturn.isPresent()) {
            this.componentsToReturn = componentsToReturn.get();
        } else {
            this.componentsToReturn = ComponentsToReturn.MEASURES;
        }

        checkArgument(!(this.componentsToReturn == ComponentsToReturn.MEASURES && this.rowsToReturn == RowsToReturn.ALL),
                "cannot use 'all' with 'measures' parameter");

        checkDataStructure(this.dataset);

        if (errorCode != null && errorCode.isPresent()) {
            checkArgument(!errorCode.get().isEmpty(), "the errorCode argument was empty");
            this.errorCode = errorCode.get();
        } else {
            this.errorCode = null;
        }

        if (errorLevel != null && errorLevel.isPresent()) {
            this.errorLevel = errorLevel.get();
        } else {
            this.errorLevel = null;
        }
    }

    private void checkDataStructure(Dataset dataset) {
        int noIdentifiers = Maps.filterValues(dataset.getDataStructure().getRoles(), role -> role == Component.Role.IDENTIFIER)
                .size();
        checkArgument(noIdentifiers > 0, "dataset does not have identifier components");

        long noBooleanMeasures = dataset.getDataStructure().values().stream().filter(c -> c.isMeasure() && c.getType().equals(Boolean.class)).count();
        checkArgument(noBooleanMeasures < 2, "dataset has too many boolean measure components");
        checkArgument(noBooleanMeasures > 0, "dataset has no boolean measure component");
    }

    @Override
    public DataStructure getDataStructure() {
        if (cache == null) {
            Map<String, Component.Role> newRoles = new HashMap<>(dataset.getDataStructure().getRoles());
            Map<String, Class<?>> newTypes = new HashMap<>(dataset.getDataStructure().getTypes());
            Set<String> oldNames = dataset.getDataStructure().keySet();

            if (componentsToReturn == ComponentsToReturn.CONDITION) {
                removeAllComponentsButIdentifiers(newRoles, newTypes, oldNames);
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

    private void removeAllComponentsButIdentifiers(Map<String, Component.Role> newRoles,
                                                   Map<String, Class<?>> newTypes, Set<String> oldNames) {
        for (String oldName : oldNames) {
            if (newRoles.get(oldName) != Component.Role.IDENTIFIER) {
                newRoles.remove(oldName);
                newTypes.remove(oldName);
            }
        }
    }

    @Override
    public Stream<Tuple> get() {
        Stream<Tuple> tupleStream = dataset.get();

        DataPoint errorCodeDataPoint = getDataStructure().wrap("errorcode", errorCode);
        DataPoint errorLevelDataPoint = getDataStructure().wrap("errorlevel", errorCode);

        if (rowsToReturn == RowsToReturn.NOT_VALID) {
            tupleStream = tupleStream.filter(tuple -> tuple.values().stream()
                    .filter(dataPoint -> dataPoint.getComponent().isMeasure() && dataPoint.getType().equals(Boolean.class))
                    .anyMatch(dataPoint -> dataPoint.get().equals(false))).peek(e -> System.out.println("value: " + e));
        } else if (rowsToReturn == RowsToReturn.VALID) {
            tupleStream = tupleStream.filter(tuple -> tuple.values().stream()
                    .filter(dataPoint -> dataPoint.getComponent().isMeasure() && dataPoint.getType().equals(Boolean.class))
                    .anyMatch(dataPoint -> dataPoint.get().equals(true))).peek(e -> System.out.println("value: " + e));
        } //else if ("all".equals(rowsToReturn)) //all is not filtered

        if (componentsToReturn == ComponentsToReturn.MEASURES) {
            tupleStream = tupleStream.map(dataPoints -> {
                        List<DataPoint> dataPointsNewList = new ArrayList<>(dataPoints);
                        dataPointsNewList.add(errorCodeDataPoint);
                        dataPointsNewList.add(errorLevelDataPoint);
                        return Tuple.create(dataPointsNewList);
                    });
        } else if (componentsToReturn == ComponentsToReturn.CONDITION) {
            //TODO here
//            new DataPoint(getDataStructure().get("CONDITION")) {
//                @Override
//                public Object get() {
//                    return dataPoints.values().stream()
//                            .filter(dp -> dp.getRole() == Component.Role.MEASURE && dp.getType().equals(Boolean.class))
//                            .findFirst().orElseThrow(() -> new IllegalArgumentException("DataPoint of type Boolean and role MEASURE not found in stream"));
//                }
//            });
        }

        return tupleStream;
    }
}
