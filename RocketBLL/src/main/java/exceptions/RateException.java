package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	
	RateDomainModel rateDomainModel = new RateDomainModel();
	
	public RateException (RateDomainModel e) {
		this.rateDomainModel = e;
	}
	
	public RateDomainModel getrateDomainModel() {
		return rateDomainModel;
	}
}
