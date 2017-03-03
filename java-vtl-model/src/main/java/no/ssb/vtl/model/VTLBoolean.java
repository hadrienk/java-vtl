package no.ssb.vtl.model;

public abstract class VTLBoolean extends VTLObject<Boolean> {
    
    public static VTLBoolean of(boolean value) {
    
        return new VTLBoolean() {
            @Override
            public Boolean get() {
                return value;
            }
        };
    }
    
}
