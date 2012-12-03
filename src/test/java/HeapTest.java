/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeapTest {

    int[] ww4 = {1, -4, 3, -2};
    int[] vv4 = {2, 1, 3, 4};
    int[] xx4 = {-1, -2, 3, 4};
    int[] uu4 = {1, 2, 3, 4};
    int[] ww46 ={1, -4, 3, -2, 5, 6};
    int[] ww6 = {-1, -6, 3, -4, 5, -2};


    Heap hw4, hv4, hx4, hu4, hw46, hw6;
    Element w4, v4, x4, u4, w46, w6;

    @Before public void setUp() {
        w4 = new Element(ww4);
        v4 = new Element(vv4);
        x4 = new Element(xx4);
        u4 = new Element(uu4);
        w46 = new Element(ww46);
        w6 = new Element(ww6);

        hw4 = new Heap(w4);
        hv4 = new Heap(v4);
        hx4 = new Heap(x4);
        hu4 = new Heap(u4);
        hw46 = new Heap(w46);
        hw6 = new Heap(w6);
    }
    
    @After public void tearDown() {
        w4 = null;
        v4 = null;
        x4 = null;
        u4 = null;
        w46 = null;
        w6 = null;

        hw4 = null;
        hv4 = null;
        hx4 = null;
        hu4 = null;
        hw46 = null;
        hw6 = null;
    }
    
    @Test public void testHeight() {
        assertEquals(0, hu4.maxHeight());
        assertEquals(3, hw4.maxHeight());
        assertEquals(3, hw46.maxHeight());
        assertEquals(5, hw6.maxHeight());
        assertEquals(1, hv4.maxHeight());
        assertEquals(1, hx4.maxHeight());
    }

    @Test public void testWidth() {
        assertEquals(0, hu4.maxWidth());
        assertEquals(4, hw4.maxWidth());
        assertEquals(4, hw46.maxWidth());
        assertEquals(6, hw6.maxWidth());
        assertEquals(2, hv4.maxWidth());
        assertEquals(2, hx4.maxWidth());
    }
}