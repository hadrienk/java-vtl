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

import org.junit.Test;

import static junit.framework.TestCase.*;

public class VTLPredicateTest {
    
    private final VTLPredicate isTrue = dataPoint -> VTLBoolean.of(true);
    private final VTLPredicate isFalse = dataPoint -> VTLBoolean.of(false);
    private final VTLPredicate isNull = dataPoint -> null;
    private final DataPoint dataPoint = DataPoint.create(0);
    
    @Test
    public void testAnd() throws Exception {
        assertTrue(isTrue.and(isTrue).apply(dataPoint).get());
        assertFalse(isTrue.and(isFalse).apply(dataPoint).get());
        assertFalse(isFalse.and(isTrue).apply(dataPoint).get());
        assertFalse(isFalse.and(isFalse).apply(dataPoint).get());
        
        assertFalse(isFalse.and(isNull).apply(dataPoint).get());
        assertFalse(isNull.and(isFalse).apply(dataPoint).get());
    
        assertNull(isNull.and(isNull).apply(dataPoint));
        assertNull(isNull.and(isTrue).apply(dataPoint));
        assertNull(isTrue.and(isNull).apply(dataPoint));
    }
    
    @Test
    public void testOr() throws Exception {
        assertTrue(isTrue.or(isTrue).apply(dataPoint).get());
        assertTrue(isTrue.or(isFalse).apply(dataPoint).get());
        assertTrue(isFalse.or(isTrue).apply(dataPoint).get());
        assertFalse(isFalse.or(isFalse).apply(dataPoint).get());
    
        assertTrue(isNull.or(isTrue).apply(dataPoint).get());
        assertTrue(isTrue.or(isNull).apply(dataPoint).get());
        
        assertNull(isFalse.or(isNull).apply(dataPoint));
        assertNull(isNull.or(isFalse).apply(dataPoint));
        assertNull(isNull.or(isNull).apply(dataPoint));
        
    }
    
    @Test
    public void testXor() throws Exception {
        assertFalse(isTrue.xor(isTrue).apply(dataPoint).get());
        assertTrue(isTrue.xor(isFalse).apply(dataPoint).get());
        assertTrue(isFalse.xor(isTrue).apply(dataPoint).get());
        assertFalse(isFalse.xor(isFalse).apply(dataPoint).get());
        
        assertNull(isNull.xor(isTrue).apply(dataPoint));
        assertNull(isTrue.xor(isNull).apply(dataPoint));
        assertNull(isFalse.xor(isNull).apply(dataPoint));
        assertNull(isNull.xor(isFalse).apply(dataPoint));
        assertNull(isNull.xor(isNull).apply(dataPoint));
    }
    
    
    
    @Test
    public void testNegate() throws Exception {
        assertFalse(isTrue.negate().apply(dataPoint).get());
        assertTrue(isFalse.negate().apply(dataPoint).get());
        assertNull(isNull.negate().apply(dataPoint));
        
    }
}
