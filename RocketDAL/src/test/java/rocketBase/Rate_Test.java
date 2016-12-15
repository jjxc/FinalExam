package rocketBase;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	
	//		Check to see if a known credit score returns a known interest rate
	
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	
	ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
	
	@Test
	public void test() {
		
		
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);	
	}
	
	
		//	Check to see if a known credit score returns a known interest rate
 
	@Test
	public void test1() {
		for(RateDomainModel rMOD : rates) {
			System.out.println(rMOD.getdInterestRate());
		}
		
		assertEquals(rates.get(0).getdInterestRate(),3.5,1);
		assertEquals(rates.get(1).getdInterestRate(),3.75,1);
		assertEquals(rates.get(2).getdInterestRate(),4.0,1);
		assertEquals(rates.get(3).getdInterestRate(),4.5,1);
		assertEquals(rates.get(4).getdInterestRate(),5.0,1);	
		
		
		//		Check to see if a RateException is thrown if there are no rates for a given
		//		credit score
	}

}
