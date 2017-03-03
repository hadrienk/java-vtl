package no.ssb.vtl.model;

import static java.lang.String.*;

public abstract class VTLNumber extends VTLObject<Number> {
    
    /**
     * Returns the value of the data point.
     */
    @Override
    public abstract Number get();
    
    public static VTLNumber of(Number number) {
        
        return new VTLNumber() {
            @Override
            public Number get() {
                return number;
            }
        };
        
    }
    
    public VTLNumber add(Number addend) {
        Number augend = get();
        if (augend instanceof Float || addend instanceof Float) {
            return VTLNumber.of(augend.floatValue() + addend.floatValue());
        } else if (augend instanceof Double || addend instanceof Double) {
                return VTLNumber.of(augend.doubleValue() + addend.doubleValue());
        } else if (augend instanceof Integer || addend instanceof Integer) {
                return VTLNumber.of(augend.intValue() + addend.intValue());
        } else if (augend instanceof Long || addend instanceof Long) {
                return VTLNumber.of(augend.longValue() + addend.longValue());
        }
    
        throw new RuntimeException(
                format("unsupported number types %s, %s", augend.getClass(), addend.getClass())
        );
        
    }
    
    public VTLNumber subtract(Number subtrahend) {
        Number minuend = get();
        if (minuend instanceof Float || subtrahend instanceof Float) {
            return VTLNumber.of(minuend.floatValue() - subtrahend.floatValue());
        } else if (minuend instanceof Double || subtrahend instanceof Double) {
            return VTLNumber.of(minuend.doubleValue() - subtrahend.doubleValue());
        } else if (minuend instanceof Integer || subtrahend instanceof Integer) {
            return VTLNumber.of(minuend.intValue() - subtrahend.intValue());
        } else if (minuend instanceof Long || subtrahend instanceof Long) {
            return VTLNumber.of(minuend.longValue() - subtrahend.longValue());
        }
        
        throw new RuntimeException(
                format("unsupported number types %s, %s", minuend.getClass(), subtrahend.getClass())
        );
        
    }
    
    public VTLNumber multiply(Number rightFactor) {
        Number leftFactor = get();
        if (leftFactor instanceof Float || rightFactor instanceof Float) {
            return VTLNumber.of(leftFactor.floatValue() * rightFactor.floatValue());
        } else if (leftFactor instanceof Double || rightFactor instanceof Double) {
            return VTLNumber.of(leftFactor.doubleValue() * rightFactor.doubleValue());
        } else if (leftFactor instanceof Integer || rightFactor instanceof Integer) {
            return VTLNumber.of(leftFactor.intValue() * rightFactor.intValue());
        } else if (leftFactor instanceof Long || rightFactor instanceof Long) {
            return VTLNumber.of(leftFactor.longValue() * rightFactor.longValue());
        }
        throw new RuntimeException(format("unsupported number types %s, %s", leftFactor.getClass(), rightFactor.getClass()));
    }
    
    public VTLNumber divide(Number divisor) {
        Number dividend = get();
        if (dividend instanceof Float || divisor instanceof Float) {
            return VTLNumber.of(dividend.floatValue() / divisor.floatValue());
        } else if (dividend instanceof Double || divisor instanceof Double) {
            return VTLNumber.of(dividend.doubleValue() / divisor.doubleValue());
        } else if (dividend instanceof Integer || divisor instanceof Integer) {
            return VTLNumber.of(dividend.intValue() / divisor.intValue());
        } else if (dividend instanceof Long || divisor instanceof Long) {
            return VTLNumber.of(dividend.longValue() / divisor.longValue());
        }
        throw new RuntimeException(format("unsupported number types %s, %s", dividend.getClass(), divisor.getClass()));
    }
}
