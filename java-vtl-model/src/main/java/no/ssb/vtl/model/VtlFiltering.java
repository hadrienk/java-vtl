package no.ssb.vtl.model;

import com.google.common.collect.ImmutableList;

import java.util.Collection;

public class VtlFiltering implements Filtering {

    private final ImmutableList<String> columns;
    private final FilteringSpecification spec;

    public VtlFiltering(FilteringSpecification spec, DataStructure structure) {
        this.columns = ImmutableList.copyOf(structure.keySet());
        this.spec = spec;
    }

    @Override
    public boolean test(DataPoint dataPoint) {
        Boolean result = true;
        for (Clause clause : getClauses()) {

            result = false;
            for (Literal literal : clause.getLiterals()) {
                VTLObject variable = findValue(dataPoint, literal);
                Collection<VTLObject> values = literal.getValues();

                for (VTLObject value : values) {
                    switch (literal.getOperator()) {
                        case IN:
                        case EQ:
                            result = variable.compareTo(value) == 0;
                            break;
                        case GT:
                            result = variable.compareTo(value) > 0;
                            break;
                        case GE:
                            result = variable.compareTo(value) >= 0;
                            break;
                        }
                }
                if (result ^ literal.isNegated()) {
                    break;
                }
            }
            if (!result) {
                break;
            }
        }
        return result;
    }

    private VTLObject findValue(DataPoint dataPoint, Literal literal) {
        int index = columns.indexOf(literal.getColumn());
        return dataPoint.get(index);
    }

    @Override
    public Collection<Clause> getClauses() {
        return spec.getClauses();
    }
}
