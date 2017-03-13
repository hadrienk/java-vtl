package no.ssb.vtl.model;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents a VTL variant of a predicate (boolean-valued function) of one argument.
 * Instead of a boolean primitive it returns a VTLBoolean and are an extension of the Function interface but emulates predicate behavior.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object)}. It also contains a {@link #test(DataPoint)} method that redirects to apply
 */
@FunctionalInterface
public interface VTLPredicate extends Function<DataPoint, VTLBoolean> {
    
    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Returns a predicate that evaluates to null if either predicates are {@code null}
     * (unless one is false, in that case it evaluates to {@code false})
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the {@code other} predicate
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
     * If this predicate evaluates to {@code null}, then the resulting predicate will as well
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
     * <p>Returns a predicate that evaluates to null if either predicates are {@code null}
     * (unless one is true, in that case it evaluates to {@code true})
 
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * OR of this predicate and the {@code other} predicate
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
    
    /**
     * Returns a composed predicate that represents a logical
     * Exclusive OR of this predicate and another.
     *
     * <p>Returns a predicate that evaluates to {@code null} if either predicates are {@code null}
     
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-XORed with this
     *              predicate
     * @return a composed predicate that represents the logical
     * XOR of this predicate and the {@code other} predicate
     */
    default VTLPredicate xor(VTLPredicate other) {
        return this.or(other).and(this.and(other).negate());
    }
    
    
    /**
     * Emulates the predicate test method. Equivalent to apply for this function
     * @return result of this predicate as a VTLBoolean
     */
    default VTLBoolean test(DataPoint t){
        return apply(t);
    }
    
    /**
     * Returns a {@link Predicate} based on this VTLPredicate.
     * When evaluating the resulting predicate, {@code ifNull} will be returned
     * when this VTLPredicate returns {@code null}.
     *
     * @param ifNull the boolean value used as default if this predicate is {@code null}
     *
     * @return a {@link Predicate} based on this nullable predicate
     * but with {@code ifNull} substituted for null values
     */
    default Predicate<DataPoint> toPredicate(boolean ifNull) {
        return dataPoint -> {
            VTLBoolean vtlBoolean = apply(dataPoint);
            return vtlBoolean == null ? ifNull : vtlBoolean.get();
        };
    }
    
}
