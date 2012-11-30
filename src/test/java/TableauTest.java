/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TableauTest {

    int[] ww4 = {1, -4, 3, -2};
    int[] vv4 = {1, 3, -4, -2};
    int[] uu4 = {1, 2, 3, 4};
    int[] ww46 = {1, -4, 3, -2, 5, 6};
    int[] ww6 = {-1, -6, 3, -4, 5, -2};
    int[] vv6 = {-6, -1, 3, -4, -2, 5};

    Tableau tw4, tv4, tu4, tw46, tw6, tv6;
    Element w4, v4, u4, w46, w6, v6;

    @Before public void setUp() {
        w4 = new Element(ww4);
        v4 = new Element(vv4);
        u4 = new Element(uu4);
        w46 = new Element(ww46);
        w6 = new Element(ww6);
        v6 = new Element(vv6);

        tw4 = new Tableau(w4);
        tv4 = new Tableau(v4);
        tu4 = new Tableau(u4);
        tw46 = new Tableau(w46);
        tw6 = new Tableau(w6);
        tv6 = new Tableau(v6);
    }
    
    @After public void tearDown() {
        w4 = null;
        v4 = null;
        u4 = null;
        w46 = null;
        w6 = null;
        v6 = null;
        tw4 = null;
        tv4 = null;
        tu4 = null;
        tw46 = null;
        tw6 = null;
        tv6 = null;
    }
    
    @Test public void testConstructor() {
        assertTrue(tw4.equals(tv4));
        assertFalse(tw4.equals(tu4));
        assertFalse(tw4.equals(tw46));
        assertTrue(tw6.equals(tv6));
    }

    @Test public void testGetSize() {
        assertEquals(4,tw4.getSize());
        assertEquals(6,tw6.getSize());
        assertEquals(6,tw46.getSize());
    }

    @Test public void testMaxWidth() {
        assertEquals(4,tw4.maxWidth());
        assertEquals(5,tw6.maxWidth());
        assertEquals(8,tw46.maxWidth());
    }

    @Test public void testMaxHeight() {
        assertEquals(3,tw4.maxHeight());
        assertEquals(4,tw6.maxHeight());
        assertEquals(3,tw46.maxHeight());
    }

    @Test public void testGetDomino() {
        Domino tw44 = new Domino(4,2,2,true);
        Domino tw65 = new Domino(5,4,1,false);
        
        assertTrue(tw4.getDomino(4).equals(tw44));
        assertTrue(tw6.getDomino(5).equals(tw65));
    }


}