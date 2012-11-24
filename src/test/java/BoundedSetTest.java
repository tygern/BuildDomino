/**
 * Copyright (c) 2011 by Tyson Gern
 * Licensed under the MIT License 
 */

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoundedSetTest {

    BoundedSet smallSet, medSet, medSetsl, medSetss, medSetse, medSetll, medSetls, medSetle, medSetel, medSetes, medSetee;

    @Before public void setUp() {
        smallSet = new BoundedSet(1,3);
        medSet = new BoundedSet(-4,8);

        medSetsl = new BoundedSet(-5,9);
        medSetss = new BoundedSet(-5,7);
        medSetse = new BoundedSet(-5,8);
        medSetll = new BoundedSet(-3,9);
        medSetls = new BoundedSet(-3,7);
        medSetle = new BoundedSet(-3,8);
        medSetel = new BoundedSet(-4,9);
        medSetes = new BoundedSet(-4,7);
        medSetee = new BoundedSet(-4,8);
    }
    
    @After public void tearDown() {
        smallSet = null;
    }
    
    @Test
    public void testSize() {
        assertTrue(smallSet.isEmpty());
        smallSet.add(2);
        assertFalse(smallSet.isEmpty());
        assertEquals(1, smallSet.getSize());
    }

    @Test
    public void testAdd() {
        medSet.add(2);
        assertEquals(1, medSet.getSize());
        medSet.add(-2);
        assertEquals(2, medSet.getSize());
        medSet.add(4);
        assertEquals(3, medSet.getSize());
        medSet.add(8);
        assertEquals(4, medSet.getSize());
        medSet.add(4);
        assertEquals(4, medSet.getSize());
    }

    @Test
    public void testRemove() {
        medSet.add(2).add(-2).add(4).add(8);

        medSet.remove(2);
        assertEquals(3, medSet.getSize());
        medSet.remove(3);
        assertEquals(3, medSet.getSize());
        medSet.remove(4);
        assertEquals(2, medSet.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeBig() throws Exception {
        smallSet.add(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeSmall() throws Exception {
        smallSet.add(-3);
    }

    @Test
    public void testEquals() {
        medSet.add(2); medSet.add(4); medSet.add(6);

        medSetsl.add(2).add(4).add(6);
        medSetss.add(2).add(4).add(6);
        medSetse.add(2).add(4).add(6);
        medSetll.add(2).add(4).add(6);
        medSetls.add(2).add(4).add(6);
        medSetle.add(2).add(4).add(6);
        medSetel.add(2).add(4).add(6);
        medSetes.add(2).add(4).add(6);
        medSetee.add(2).add(4).add(6);

        assertTrue(medSet.equals(medSetsl));
        assertTrue(medSet.equals(medSetss));
        assertTrue(medSet.equals(medSetse));
        assertTrue(medSet.equals(medSetll));
        assertTrue(medSet.equals(medSetls));
        assertTrue(medSet.equals(medSetle));
        assertTrue(medSet.equals(medSetel));
        assertTrue(medSet.equals(medSetes));
        assertTrue(medSet.equals(medSetee));

        medSetsl.remove(2);
        medSetss.remove(2);
        medSetse.remove(2);
        medSetll.remove(2);
        medSetls.remove(2);
        medSetle.remove(2);
        medSetel.remove(2);
        medSetes.remove(2);
        medSetee.remove(2);

        assertFalse(medSet.equals(medSetsl));
        assertFalse(medSet.equals(medSetss));
        assertFalse(medSet.equals(medSetse));
        assertFalse(medSet.equals(medSetll));
        assertFalse(medSet.equals(medSetls));
        assertFalse(medSet.equals(medSetle));
        assertFalse(medSet.equals(medSetel));
        assertFalse(medSet.equals(medSetes));
        assertFalse(medSet.equals(medSetee));

        medSetsl.add(5);
        medSetss.add(5);
        medSetse.add(5);
        medSetll.add(5);
        medSetls.add(5);
        medSetle.add(5);
        medSetel.add(5);
        medSetes.add(5);
        medSetee.add(5);

        assertFalse(medSet.equals(medSetsl));
        assertFalse(medSet.equals(medSetss));
        assertFalse(medSet.equals(medSetse));
        assertFalse(medSet.equals(medSetll));
        assertFalse(medSet.equals(medSetls));
        assertFalse(medSet.equals(medSetle));
        assertFalse(medSet.equals(medSetel));
        assertFalse(medSet.equals(medSetes));
        assertFalse(medSet.equals(medSetee));

        medSetsl.add(2);
        medSetss.add(2);
        medSetse.add(2);
        medSetll.add(2);
        medSetls.add(2);
        medSetle.add(2);
        medSetel.add(2);
        medSetes.add(2);
        medSetee.add(2);

        assertFalse(medSet.equals(medSetsl));
        assertFalse(medSet.equals(medSetss));
        assertFalse(medSet.equals(medSetse));
        assertFalse(medSet.equals(medSetll));
        assertFalse(medSet.equals(medSetls));
        assertFalse(medSet.equals(medSetle));
        assertFalse(medSet.equals(medSetel));
        assertFalse(medSet.equals(medSetes));
        assertFalse(medSet.equals(medSetee));
    }

    @Test
    public void testContains() {
        medSet.add(-2).add(0).add(7);
        assertTrue(medSet.contains(-2));
        assertFalse(medSet.contains(-1));
        assertTrue(medSet.contains(7));
        assertFalse(medSet.contains(3));
    }

}