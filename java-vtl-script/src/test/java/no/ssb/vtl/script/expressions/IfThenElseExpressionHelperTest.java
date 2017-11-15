package no.ssb.vtl.script.expressions;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Pawel Buczek
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

import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLString;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.SimpleBindings;

import static org.assertj.core.api.Assertions.*;

public class IfThenElseExpressionHelperTest {

    private static VTLExpression CONDITION_TRUE = new VTLExpression() {
        @Override
        public VTLObject resolve(Bindings bindings) {
            return VTLBoolean.of(true);
        }

        @Override
        public Class getVTLType() {
            return VTLBoolean.class;
        }
    };

    private static VTLExpression CONDITION_FALSE = new VTLExpression() {
        @Override
        public VTLObject resolve(Bindings bindings) {
            return VTLBoolean.of(false);
        }

        @Override
        public Class getVTLType() {
            return VTLBoolean.class;
        }
    };

    private static VTLExpression CONDITION_WITH_EXCEPTION = new VTLExpression() {
        @Override
        public VTLObject resolve(Bindings bindings) {
            throw new RuntimeException("Should not be called");
        }

        @Override
        public Class getVTLType() {
            return VTLBoolean.class;
        }
    };

    private static VTLExpression VALUE_WITH_EXCEPTION = new VTLExpression() {
        @Override
        public VTLObject resolve(Bindings bindings) {
            throw new RuntimeException("Should not be called");
        }

        @Override
        public Class getVTLType() {
            return VTLString.class;
        }
    };

    private static VTLExpression VALUE_OK = new VTLExpression() {
        @Override
        public VTLObject resolve(Bindings bindings) {
            return VTLObject.of("OK");
        }

        @Override
        public Class<?> getVTLType() {
            return VTLString.class;
        }

    };

    private static VTLExpression VALUE_ELSE = new VTLExpression() {
        @Override
        public VTLObject resolve(Bindings bindings) {
            return VTLObject.of("else");
        }

        @Override
        public Class<?> getVTLType() {
            return VTLString.class;
        }

    };

    @SuppressWarnings("Duplicates")
    @Test
    public void testResolveFirstCondition() throws Exception {
        IfThenElseExpressionHelper.Builder builder = new IfThenElseExpressionHelper.Builder(VALUE_ELSE);
        IfThenElseExpressionHelper function = builder
                .addCondition(CONDITION_TRUE, VALUE_OK)
                .addCondition(CONDITION_WITH_EXCEPTION, VALUE_OK)
                .build();

        assertThat(function.getVTLType()).isEqualTo(VTLString.class);

        VTLObject resolve = function.resolve(new SimpleBindings());
        assertThat(resolve.get()).isEqualTo("OK");
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testResolveSecondCondition() throws Exception {
        IfThenElseExpressionHelper.Builder builder = new IfThenElseExpressionHelper.Builder(VALUE_ELSE);
        IfThenElseExpressionHelper function = builder
                .addCondition(CONDITION_FALSE, VALUE_WITH_EXCEPTION)
                .addCondition(CONDITION_TRUE, VALUE_OK)
                .build();

        assertThat(function.getVTLType()).isEqualTo(VTLString.class);

        VTLObject resolve = function.resolve(new SimpleBindings());
        assertThat(resolve.get()).isEqualTo("OK");
    }

    @Test
    public void testResolveElse() throws Exception {
        IfThenElseExpressionHelper.Builder builder = new IfThenElseExpressionHelper.Builder(VALUE_ELSE);
        IfThenElseExpressionHelper function = builder
                .addCondition(CONDITION_FALSE, VALUE_WITH_EXCEPTION)
                .addCondition(new VTLExpression() {
                    @Override
                    public VTLObject resolve(Bindings bindings) {
                        return VTLBoolean.of(false);
                    }

                    @Override
                    public Class getVTLType() {
                        return VTLBoolean.class;
                    }
                }, VALUE_WITH_EXCEPTION)
                .build();

        assertThat(function.getVTLType()).isEqualTo(VTLString.class);

        VTLObject resolve = function.resolve(new SimpleBindings());
        assertThat(resolve.get()).isEqualTo("else");
    }

    @Test
    public void testFailOnInconsistentConditionType() throws Exception {
        assertThatThrownBy(() -> {
                    IfThenElseExpressionHelper.Builder builder = new IfThenElseExpressionHelper.Builder(VALUE_ELSE);
                    builder.addCondition(new VTLExpression() {
                        @Override
                        public VTLObject resolve(Bindings bindings) {
                            throw new RuntimeException("Should not be called");
                        }

                        @Override
                        public Class getVTLType() {
                            return VTLString.class;
                        }
                    }, VALUE_WITH_EXCEPTION);
                }
        )
                .as("exception when condition expression is not VTLBoolean")
                .hasMessage("Condition must return a no.ssb.vtl.model.VTLBoolean, but was no.ssb.vtl.model.VTLString")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFailOnInconsistentReturnType() throws Exception {
        assertThatThrownBy(() -> {
                    IfThenElseExpressionHelper.Builder builder = new IfThenElseExpressionHelper.Builder(VALUE_ELSE);
                    builder.addCondition(CONDITION_TRUE, new VTLExpression() {
                        @Override
                        public VTLObject resolve(Bindings bindings) {
                            throw new RuntimeException("Should not be called");
                        }

                        @Override
                        public Class getVTLType() {
                            return VTLBoolean.class;
                        }
                    });
                }
        )
                .as("exception when return expressions have different types")
                .hasMessage("All return values must have the same type no.ssb.vtl.model.VTLString" +
                        " but was no.ssb.vtl.model.VTLBoolean")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
