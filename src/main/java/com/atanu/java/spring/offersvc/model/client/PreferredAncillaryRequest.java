/**
 * 
 */
package com.atanu.java.spring.offersvc.model.client;

import java.io.Serializable;

/**
 * @author Atanu Bhowmick
 *
 */
public class PreferredAncillaryRequest implements Serializable {
	
	private static final long serialVersionUID = 8841272737218591031L;

	private String originAirportCode;
	private String destAirportCode;

	public String getOriginAirportCode() {
		return originAirportCode;
	}

	public void setOriginAirportCode(String originAirportCode) {
		this.originAirportCode = originAirportCode;
	}

	public String getDestAirportCode() {
		return destAirportCode;
	}

	public void setDestAirportCode(String destAirportCode) {
		this.destAirportCode = destAirportCode;
	}
	
	@Override
	public String toString() {
		return "PreferredAncillaryRequest : ["
				+ " originAirportCode : "+ originAirportCode
				+ " destAirportCode : " + destAirportCode
				+ " ]";
	}
}
