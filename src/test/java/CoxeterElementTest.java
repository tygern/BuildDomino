import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoxeterElementTest {

    int[] uu = {1, 2, 3, 4};
    int[] vv = {2, 1, 3, 4, 3, 3};

    CoxeterElement u, v;

    @Before public void setUp() {
        u = new CoxeterElement(uu, 4);
        v = new CoxeterElement(vv, 4);
    }
    
    @After public void tearDown() {
        u = null;
        v = null;
    }
    
    @Test(expected = NumberFormatException.class)
    public void testRangeBig() throws Exception {
        int[] big = {1,2,3,7};
        CoxeterElement bigElm = new CoxeterElement(big, 6);
    }

    @Test(expected = NumberFormatException.class)
    public void testRangeZero() throws Exception {
        int[] zero = {1,2,0,7};
        CoxeterElement zeroElm = new CoxeterElement(zero, 8);
    }

    @Test public void testToPermutation() {
        int[] uuP = {-1, 3, 4, -2};
        Element uP = new Element(uuP);

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

}