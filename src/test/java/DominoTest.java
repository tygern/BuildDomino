/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DominoTest{

    Domino dominoA, dominoB, dominoC, dominoD, dominoE, dominoF;

    @Before public void setUp() {
        dominoA = new Domino(2,1,1,true);
        dominoB = new Domino(2,1,1,false);
        dominoC = new Domino(2,1,2,true);
        dominoD = new Domino(3,2,1,false);
        dominoE = new Domino(2,1,1,true);
        dominoF = new Domino(2,1,1,false);
    }
    
    @After public void tearDown() {
        dominoA = null;
        dominoB = null;
        dominoC = null;
        dominoD = null;
        dominoE = null;
        dominoF = null;
    }
    
    @Test public void testEquals() {
        assertTrue(dominoA.equals(dominoE));
        assertTrue(dominoF.equals(dominoF));
        assertFalse(dominoA.equals(dominoB));
        assertFalse(dominoA.equals(dominoC));
        assertFalse(dominoA.equals(dominoD));
        assertFalse(dominoA.equals(dominoF));
    } 

    @Test public void testFlip() {
        dominoA.flipDomino();
        assertTrue(dominoA.equals(dominoB));
        dominoA.flipDomino();
        assertTrue(dominoA.equals(dominoE));
    }

    @Test public void testMove() {
        dominoA.moveDomino(1,2);
        assertTrue(dominoA.equals(dominoC));
        dominoA.flipDomino();
        dominoA.moveDomino(1,1);
        assertTrue(dominoA.equals(dominoB));
    }

    @Test public void testOverlap() {
        assertEquals(1,dominoA.overlap(dominoB));
        assertEquals(1,dominoA.overlap(dominoC));
        assertEquals(2,dominoA.overlap(dominoE));
        assertEquals(1,dominoB.overlap(dominoD));
        assertEquals(2,dominoB.overlap(dominoF));
    }

    @Test public void testBiggerLabel() {
        assertFalse(dominoB.biggerLabel(dominoD));
        assertTrue(dominoD.biggerLabel(dominoB));
        assertFalse(dominoA.biggerLabel(dominoF));
    }

}