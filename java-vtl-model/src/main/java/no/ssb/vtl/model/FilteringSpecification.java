package no.ssb.vtl.model;

import java.util.Collection;

public interface FilteringSpecification {

    Collection<? extends FilteringSpecification> getOperands();

    Operator getOperator();

    String getColumn();

    VTLObject getValue();

    Boolean isNegated();

    enum Operator {
        EQ, GT, LT, AND, OR, TRUE
    }

}
