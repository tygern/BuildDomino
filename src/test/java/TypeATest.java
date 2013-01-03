/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TypeATest {

    TypeA u, v, w, x, y, z;

    int[] uu = {1, 2, 3, 4};
    int[] vv = {1, 2, 3, 4};
    int[] ww = {1, 4, 3, 2};
    int[] xx = {1, 2, 3, 4, 5, 6};
    int[] yy = {1, 3, 4, 2};
    int[] zz = {1, 3, 2, 4};

    @Before public void setUp() {
        u = new TypeA(uu);
        v = new TypeA(vv);
        w = new TypeA(ww);
        x = new TypeA(xx);
        y = new TypeA(yy);
        z = new TypeA(zz);
    }
    
    @After public void tearDown() {
        u = null;
        v = null;
        w = null;
        x = null;
        y = null;
        z = null;
    }
    
    @Test public void testEquals() {
        assertTrue(u.equals(v));
        assertFalse(u.equals(w));
        assertFalse(u.equals(x));
    } 

    @Test(expected = IllegalArgumentException.class)
    public void testRangeBig() throws Exception {
        int[] big = {7,1,2,3};
        TypeA bigElm = new TypeA(big);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeZero() throws Exception {
        int[] zero = {4,0,2,3};
        TypeA zeroElm = new TypeA(zero);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRepeat() throws Exception {
        int[] repeat = {2,1,2,3};
        TypeA repeatElm = new TypeA(repeat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegative() throws Exception {
        int[] sign = {-4,1,2,3};
        TypeA signElm = new TypeA(sign);
    }

    @Test public void testGetRank() {
        assertEquals(3,w.getRank());
        assertEquals(5,x.getRank());
    } 
    
    @Test public void testMapsTo() {
        assertEquals(3,v.mapsTo(3));
        assertEquals(2,w.mapsTo(4));
        assertEquals(0,w.mapsTo(5));
        assertEquals(4,y.mapsTo(3));
    } 
    
    @Test public void testMapsFrom() {
        assertEquals(3,v.mapsFrom(3));
        assertEquals(4,w.mapsFrom(2));
        assertEquals(2,w.mapsFrom(4));
        assertEquals(0,w.mapsFrom(5));
    } 
    
    @Test public void testFindInverse() {
        int[] yi = {1, 4, 2, 3};
        TypeA yInv = new TypeA(yi);
        
        assertTrue(v.findInverse().equals(v));
        assertTrue(w.findInverse().equals(w));
        assertTrue(y.findInverse().equals(yInv));
        assertTrue(y.findInverse().findInverse().equals(y));
    } 

    @Test public void testLength() {
        assertEquals(0,u.length());
        assertEquals(3,w.length());
        assertEquals(1,z.length());
        assertEquals(2,y.length());
    } 

    /*
    @Test public void testRightMultiply() {
        int[] yyww = {1, 2, 4, 3};
        TypeA yw = new TypeA(yyww);
        
        assertTrue(u.rightMultiply(v).equals(u));
        assertTrue(w.rightMultiply(y).equals(z));
        assertTrue(u.rightMultiply(x) == null);
    }
    @Test public void testLeftMultiply() {
        assertTrue(u.leftMultiply(v).equals(u));
        assertTrue(u.leftMultiply(x) == null);
        assertTrue(y.leftMultiply(w).equals(z));
    }
    */
}