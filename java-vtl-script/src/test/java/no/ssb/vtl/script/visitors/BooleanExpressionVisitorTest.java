package no.ssb.vtl.script.visitors;

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLPredicate;
import no.ssb.vtl.parser.VTLParser;
import org.junit.Before;
import org.junit.Test;

import java.util.function.BiPredicate;

import static org.junit.Assert.*;

public class BooleanExpressionVisitorTest {
    
    private BooleanExpressionVisitor visitor;
    private DataStructure dataStructure;
    private DataPoint dataPointWithValues;
    private DataPoint dataPointWithNull;
    
    @Before
    public void setUp() throws Exception {
        dataStructure = DataStructure.of(
                (o, aClass) -> o,
                "id1", Component.Role.IDENTIFIER, String.class,
                "m1", Component.Role.MEASURE, Integer.class,
                "m2", Component.Role.MEASURE, Double.class,
                "m3", Component.Role.MEASURE, Integer.class,
                "at1", Component.Role.ATTRIBUTE, String.class);
        visitor = new BooleanExpressionVisitor(null, dataStructure);
        dataPointWithValues = dataStructure.wrap(ImmutableMap.of("id1", "1", "m1", 20, "m2", 10.0, "m3", 10, "at1", "blabla"));
        dataPointWithNull = dataStructure.wrap();
        
        
    }

    @Test
    public void testIsNull() throws Exception {
        VTLPredicate isNull = visitor.getIsNullPredicate(dataStructure.get("id1"));

        assertFalse(isNull.test(dataPointWithValues).get());
        assertTrue(isNull.test(dataPointWithNull).get());

        VTLPredicate isNullWithNull = visitor.getIsNullPredicate(null);
        assertTrue(isNullWithNull.test(dataPointWithValues).get());
        assertTrue(isNullWithNull.test(dataPointWithNull).get());

        VTLPredicate isNullWithNonNull = visitor.getIsNullPredicate(1);
        assertFalse(isNullWithNonNull.test(dataPointWithValues).get());
        assertFalse(isNullWithNonNull.test(dataPointWithNull).get());

    }

    @Test
    public void testNot() throws Exception {

        VTLPredicate stringEqualsTrue = visitor.getVtlPredicate(dataStructure.get("id1"), "1", op(VTLParser.EQ));
        VTLPredicate stringEqualsFalse = visitor.getVtlPredicate(dataStructure.get("id1"), "1", op(VTLParser.NE));
        VTLPredicate notTrue = visitor.getNotPredicate(stringEqualsTrue);
        VTLPredicate notFalse = visitor.getNotPredicate(stringEqualsFalse);

        assertFalse(notTrue.test(dataPointWithValues).get());
        assertNull(notTrue.test(dataPointWithNull));

        assertTrue(notFalse.test(dataPointWithValues).get());
        assertNull(notFalse.test(dataPointWithNull));

    }

    @Test
    public void testEqualityWithValues() throws Exception {
        VTLPredicate stringEquals = visitor.getVtlPredicate(dataStructure.get("id1"), "1", op(VTLParser.EQ));
        assertTrue(stringEquals.test(dataPointWithValues).get());
    
        VTLPredicate geDouble = visitor.getVtlPredicate(10.1, dataStructure.get("m2"), op(VTLParser.GE));
        assertTrue(geDouble.test(dataPointWithValues).get());
    
        VTLPredicate leInt = visitor.getVtlPredicate(dataStructure.get("m1"), dataStructure.get("m3"), op(VTLParser.LE));
        assertFalse(leInt.test(dataPointWithValues).get());
    
        VTLPredicate neConst = visitor.getVtlPredicate(1, 2, op(VTLParser.NE));
        assertTrue(neConst.test(dataPointWithValues).get());
    }
    
    @Test
    public void testEqualityWithNull() throws Exception {
        VTLPredicate nullEquals = visitor.getVtlPredicate(dataStructure.get("id1"), null, op(VTLParser.EQ));
        assertNull(nullEquals.test(dataPointWithValues));
    
        VTLPredicate stringEquals = visitor.getVtlPredicate(dataStructure.get("id1"), "1", op(VTLParser.EQ));
        assertNull(stringEquals.test(dataPointWithNull));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCompareIncompatibleTypes() throws Exception {
        VTLPredicate compareIncompatible = visitor.getVtlPredicate(dataStructure.get("m1"), dataStructure.get("m2"), op(VTLParser.LT));
        compareIncompatible.test(dataPointWithValues);
        fail("No Exception was thrown despite trying to compare incompatible types");
    }
    
    private BiPredicate<VTLObject, VTLObject> op(int op) {
        return visitor.getBooleanOperation(op);
    }
}