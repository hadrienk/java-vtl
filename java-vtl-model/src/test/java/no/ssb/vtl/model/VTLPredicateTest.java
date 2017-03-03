package no.ssb.vtl.model;

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