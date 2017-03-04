package no.ssb.vtl.model;

public abstract class VTLBoolean extends VTLObject<Boolean> {
    
    public static VTLBoolean of(Boolean value) {
    
        if (value == null) {
            return null;
        } else {
            return new VTLBoolean() {
                @Override
                public Boolean get() {
                    return value;
                }
            };
        }
    }
    
}
