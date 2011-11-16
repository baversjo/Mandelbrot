import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestComplex {
	private Complex c,c2;
	
	@Before
    public void setUp() {
		 c = new Complex(3,4);
		 c2 = new Complex(1,2);
    }
	
	@Test
	public void testCreateComplex() {
		assertEquals("get Real",3,c.getRe(),0);
		assertEquals("get Imaginary",4,c.getIm(),0);
	}
	@Test
	public void testAbsoluteValue(){
		assertEquals("calculate ABS", 5, c.getAbs2(),0);
	}
	@Test
	public void testAddition(){
		c.add(c2);
		assertEquals("addition re",4,c.getRe(),0);
		assertEquals("addition im",6,c.getIm(),0);
	}
	@Test
	public void testMultiplication(){
		c.mul(c2);
		assertEquals("multiplication re",-5,c.getRe(),0);
		assertEquals("multiplication im",10,c.getIm(),0);
	}
	@Test
	public void testMultiplicationSame(){
		c.mul(c);
		assertEquals("multiplication re",-7,c.getRe(),0);
		assertEquals("multiplication im",24,c.getIm(),0);
	}

}
