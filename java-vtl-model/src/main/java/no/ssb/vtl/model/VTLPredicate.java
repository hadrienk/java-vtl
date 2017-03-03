package no.ssb.vtl.model;

import java.util.function.Function;

@FunctionalInterface
public interface VTLPredicate extends Function<DataPoint, VTLBoolean> {
    
    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default VTLPredicate and(VTLPredicate other) {
        return (t) -> {
            VTLBoolean result = apply(t);
            VTLBoolean otherResult = other.apply(t);
            
            if (result != null) {
                if (!result.get()) {
                    return VTLBoolean.of(false);
                } else {
                    if (otherResult != null) {
                        return VTLBoolean.of(result.get() && otherResult.get());
                    } else {
                        return null;
                    }
                }
            } else {
                if (otherResult != null) {
                    if (!otherResult.get()) {
                        return VTLBoolean.of(false);
                    } else {
                        return null;
                    }
                }
                return null;
            }
        };
    }
    
    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     * predicate
     */
    default VTLPredicate negate() {
        return (t) -> apply(t) == null ? null : VTLBoolean.of(!apply(t).get());
    }
    
    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default VTLPredicate or(VTLPredicate other) {
        return (t) -> {
            VTLBoolean result = apply(t);
            VTLBoolean otherResult = other.apply(t);
        
            if (result != null) {
                if (result.get()) {
                    return VTLBoolean.of(true);
                } else {
                    if (otherResult != null) {
                        return VTLBoolean.of(result.get() || otherResult.get());
                    } else {
                        return null;
                    }
                }
            } else {
                if (otherResult != null) {
                    if (otherResult.get()) {
                        return VTLBoolean.of(true);
                    } else {
                        return null;
                    }
                }
                return null;
            }
        };
    }
    
    default VTLPredicate xor(VTLPredicate other) {
        return this.or(other).and(this.and(other).negate());
    }
    
}
