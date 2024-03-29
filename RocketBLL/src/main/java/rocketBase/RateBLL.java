package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.FinanceLib;

import exceptions.RateException;
import rocketData.LoanRequest;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		

		ArrayList<RateDomainModel> alRates = _RateDAL.getAllRates();
	
		double currentRate = 0.0;
			for (RateDomainModel r : alRates) {
				if (GivenCreditScore >= r.getiMinCreditScore()) {
					currentRate = r.getdInterestRate();
				}
			
			if (currentRate < 0.0) {
				throw new RateException(r);
			}
			}
				
		
		//			obviously this should be changed to return the determined rate
		return currentRate;
		
	}
		
	
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return Math.abs(FinanceLib.pmt(r, n, p, f, t));
	}
	
}

