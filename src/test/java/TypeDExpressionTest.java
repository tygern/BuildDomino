/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TypeDExpressionTest {

    int[] uu = {1, 2, 3, 4};
    int[] vv = {2, 1, 3, 4, 3, 3};

    TypeDExpression u, v;

    @Before public void setUp() {
        u = new TypeDExpression(uu, 4);
        v = new TypeDExpression(vv, 4);
    }
    
    @After public void tearDown() {
        u = null;
        v = null;
    }
    
    @Test(expected = NumberFormatException.class)
    public void testRangeBig() throws Exception {
        int[] big = {1,2,3,7};
        TypeDExpression bigElm = new TypeDExpression(big, 6);
    }

    @Test(expected = NumberFormatException.class)
    public void testRangeZero() throws Exception {
        int[] zero = {1,2,0,7};
        TypeDExpression zeroElm = new TypeDExpression(zero, 8);
    }

    @Test public void testToPermutation() {
        int[] uuP = {-1, 3, 4, -2};
        TypeD uP = new TypeD(uuP);

        assertTrue(u.toPermutation().equals(uP));
        assertTrue(u.toPermutation().equals(v.toPermutation()));
    }

    @Test public void testReduce() {
        assertTrue(u.reduce().equals(v.reduce()));
    }

    @Test public void testIsReduced() {
        assertTrue(u.isReduced());
        assertFalse(v.isReduced());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRightMultiplyRank() throws Exception {
        int[] ww = {1, 2, 3, 4, 2, 1, 3, 4};
        TypeDExpression w = new TypeDExpression(ww, 6);
        u.rightMultiply(w);
    }

    @Test public void testRightMultiply() {
        int[] ww = {1, 2, 3, 4, 2, 1, 3, 4};
        TypeDExpression w = new TypeDExpression(ww, 4);
        assertTrue(u.rightMultiply(v).equals(w));
    }

    @Test public void testLeftMultiply() {
        int[] ww = {1, 2, 3, 4, 2, 1, 3, 4};
        TypeDExpression w = new TypeDExpression(ww, 4);
        assertTrue(v.rightMultiply(u).equals(w));
    }

}