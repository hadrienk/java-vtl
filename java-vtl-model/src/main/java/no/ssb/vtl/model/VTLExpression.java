package no.ssb.vtl.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Function;

public abstract class VTLExpression extends VTLObject implements Function<DataPoint, VTLObject> {
    
    /**
     * Returns the value of the data point.
     */
    @Override
    public Object get() {
        throw new NotImplementedException();
    }
    
    @Override
    public Class<?> getType() {
        return super.getType();
    }
    
    
    @Override
    public String toString() {
        return "VTLExpression";
    }
    
    
    public static class Builder {
        private final Function<DataPoint, VTLObject> function;
        private final Class<?> type;
        private String description;
    
        public Builder(Class<?> type, Function<DataPoint, VTLObject> function) {
            this.function = function;
            this.type = type;
        }
    
        public Builder description(String description) {
            this.description = description;
            return this;
        }
    
        public VTLExpression build() {
            return new VTLExpression() {
                @Override
                public VTLObject apply(DataPoint dataPoint) {
                    return function.apply(dataPoint);
                }
        
                @Override
                public Class<?> getType() {
                    return type;
                }
        
                @Override
                public String toString() {
                    return description;
                }
            };
        }
    }
}
