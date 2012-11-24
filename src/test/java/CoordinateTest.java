/**
 * Copyright (c) 2011 by Tyson Gern
 * Licensed under the MIT License 
 */

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoordinateTest{

    Coordinate pointA;
    Coordinate pointB;
    Coordinate pointC;
    Coordinate pointD;

    @Before public void setUp() {
        pointA = new Coordinate(1,2);
        pointB = new Coordinate(0,-4);
        pointC = new Coordinate(1,3);
        pointD = new Coordinate(1,2);
    }
    
    @After public void tearDown() {
        pointA = null;
        pointB = null;
        pointC = null;
        pointD = null;
    }
    
    @Test
    public void testEquals() {
        assertTrue(pointA.equals(pointD));
        assertFalse(pointA.equals(pointB));
        assertFalse(pointA.equals(pointC));
    }

    @Test
    public void testGetters() {
        assertEquals(1,pointA.getXVal());
        assertEquals(2,pointA.getYVal());
    }

    @Test
    public void testSetters() {
        pointA.setXVal(7);
        assertEquals(7,pointA.getXVal());

        pointA.setYVal(6);
        assertEquals(6,pointA.getYVal());
    }



}