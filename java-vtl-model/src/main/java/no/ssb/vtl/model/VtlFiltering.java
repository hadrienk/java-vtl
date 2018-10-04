package no.ssb.vtl.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public class VtlFiltering implements Filtering {

    private final ImmutableList<String> columns;
    private final FilteringSpecification spec;

    public VtlFiltering(FilteringSpecification spec, DataStructure structure) {
        this.columns = ImmutableList.copyOf(structure.keySet());
        this.spec = spec;
    }

    public VtlFiltering(List<List<Literal>> spec, DataStructure structure) {
        this.columns = ImmutableList.copyOf(structure.keySet());
        List<Clause> clauses = Lists.newArrayList();
        for (List<Literal> literals : spec) {
            clauses.add(() -> literals);
        }
        this.spec = () -> clauses;
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
                    result = literal.isNegated() ^ result;
                }
                if (result) {
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
