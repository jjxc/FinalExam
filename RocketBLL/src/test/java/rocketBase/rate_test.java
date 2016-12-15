package rocketBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void test() {
		assert(1==1);
	}
	
	@Test
	public void getratetest1() throws RateException {
		assertEquals(RateBLL.getRate(700),4,1);
	}
	
	@Test
	public void getratetest2() throws RateException {
		assertTrue(RateBLL.getRate(400) != 4.5);
	}
	

}
