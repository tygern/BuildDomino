/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ElementTest {

    Element u, v, w, x, y, z;

    int[] uu = {1, 2, 3, 4};
    int[] vv = {1, 2, 3, 4};
    int[] ww = {1, -4, 3, -2};
    int[] xx = {1, 2, 3, 4, 5, 6};
    int[] yy = {1, 3, -4, -2};
    int[] zz = {1, 3, 2, 4};

    @Before public void setUp() {
        u = new Element(uu);
        v = new Element(vv);
        w = new Element(ww);
        x = new Element(xx);
        y = new Element(yy);
        z = new Element(zz);
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
        int[] big = {-7,1,-2,3};
        Element bigElm = new Element(big);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeZero() throws Exception {
        int[] zero = {-4,0,-2,3};
        Element zeroElm = new Element(zero);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRepeat() throws Exception {
        int[] repeat = {2,1,-2,3};
        Element repeatElm = new Element(repeat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSign() throws Exception {
        int[] sign = {-4,1,2,3};
        Element signElm = new Element(sign);
    }

    @Test public void testGetSign() {
        assertEquals(1,u.getSign(1));
        assertEquals(-1,w.getSign(2));
    } 

    @Test public void testGetRank() {
        assertEquals(4,w.getRank());
        assertEquals(6,x.getRank());
    } 
    
    @Test public void testMapsTo() {
        assertEquals(3,v.mapsTo(3));
        assertEquals(-3,v.mapsTo(-3));
        assertEquals(-2,w.mapsTo(4));
        assertEquals(2,w.mapsTo(-4));
        assertEquals(0,w.mapsTo(5));
        assertEquals(4,y.mapsTo(-3));
    } 
    
    @Test public void testMapsFrom() {
        assertEquals(3,v.mapsFrom(3));
        assertEquals(4,w.mapsFrom(-2));
        assertEquals(-2,w.mapsFrom(4));
        assertEquals(0,w.mapsFrom(5));
    } 
    
    @Test public void testFindInverse() {
        int[] yi = {1, -4, 2, -3};
        Element yInv = new Element(yi);
        
        assertTrue(v.findInverse().equals(v));
        assertTrue(w.findInverse().equals(w));
        assertTrue(y.findInverse().equals(yInv));
        assertTrue(y.findInverse().findInverse().equals(y));
    } 

    @Test public void testIsBad() {
        int[] ww4 = {1, -4, 3, -2};
        int[] ww6 = {-1, -6, 3, -4, 5, -2};
        int[] ww8 = {1, -8, 3, -6, 5, -4, 7, -2};

        Element w4 = new Element(ww4);
        Element w6 = new Element(ww6);
        Element w8 = new Element(ww8);
        
        assertTrue(w4.isBad());
        assertTrue(w6.isBad());
        assertTrue(w8.isBad());
        assertFalse(v.isBad());
        assertFalse(y.isBad());
        assertFalse(z.isBad());
    } 

    @Test public void testLength() {
        assertEquals(0,u.length());
        assertEquals(7,w.length());
        assertEquals(1,z.length());
        assertEquals(8,y.length());
    } 

    @Test public void testRightDescent() {
        BoundedSet wD = new BoundedSet(1,4);
        BoundedSet uD = new BoundedSet(1,4);
        BoundedSet zD = new BoundedSet(1,4);

        wD.add(1).add(2).add(4);
        zD.add(3);
        
        assertTrue(w.rightDescent().equals(wD));
        assertTrue(u.rightDescent().equals(uD));
        assertTrue(z.rightDescent().equals(zD));
    } 

    @Test public void testFindRE() {
        assertTrue(u.findRE().toPermutation().equals(u));
        assertTrue(w.findRE().toPermutation().equals(w));
        assertTrue(x.findRE().toPermutation().equals(x));
        assertTrue(y.findRE().toPermutation().equals(y));
        assertTrue(z.findRE().toPermutation().equals(z));
    } 

    @Test public void testRightMultiply() {
        int[] yyww = {1, 2, -4, -3};
        Element yw = new Element(yyww);
        
        assertTrue(u.rightMultiply(v).equals(u));
        assertTrue(w.rightMultiply(y).equals(z));
        assertTrue(y.rightMultiply(w).equals(yw));
        assertTrue(u.rightMultiply(x) == null);
    }
    @Test public void testLeftMultiply() {
        assertTrue(u.leftMultiply(v).equals(u));
        assertTrue(u.leftMultiply(x) == null);
        assertTrue(y.leftMultiply(w).equals(z));
    }

}