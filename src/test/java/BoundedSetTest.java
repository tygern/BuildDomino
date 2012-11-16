import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoundedSetTest{

    BoundedSet smallSet;
    BoundedSet medSet;

    @Before public void setUp() {
        smallSet = new BoundedSet(1,3);
        medSet = new BoundedSet(-4,8);
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



}