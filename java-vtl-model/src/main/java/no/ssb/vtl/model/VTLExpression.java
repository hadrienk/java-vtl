package no.ssb.vtl.model;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import javax.script.Bindings;
import java.util.function.Function;

/**
 * Base VTL expression
 *
 * Represents a scalar value (VTLObject) whose computation is function of a Bindings but still exposes its type.
 */
public abstract class VTLExpression implements Function<Bindings, VTLObject>, VTLTyped {

    @Override
    public abstract Class<?> getType();
    
    @Override
    public String toString() {
        return "VTLExpression";
    }
    
    public static class Builder {
        private final Function<Bindings, VTLObject> function;
        private final Class<?> type;
        private String description;
    
        public Builder(Class<?> type, Function<Bindings, VTLObject> function) {
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
                public VTLObject apply(Bindings dataPoint) {
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
