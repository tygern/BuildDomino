import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ElementTest {

    Element u, v, w, x, y, z;

    int[] a = {1, 2, 3, 4};
    int[] b = {1, 2, 3, 4};
    int[] c = {1, -4, 3, -2};
    int[] d = {1, 2, 3, 4, 5, 6};
    int[] e = {1, 3, -4, -2};
    int[] f = {1, 3, 2, 4};

    @Before public void setUp() {
        u = new Element(a);
        v = new Element(b);
        w = new Element(c);
        x = new Element(d);
        y = new Element(e);
        z = new Element(f);
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

}