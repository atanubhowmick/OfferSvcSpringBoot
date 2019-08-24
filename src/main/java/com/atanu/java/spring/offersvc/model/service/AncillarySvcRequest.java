/**
 * 
 */
package com.atanu.java.spring.offersvc.model.service;

import java.io.Serializable;

/**
 * @author Atanu Bhowmick
 *
 */
public class AncillarySvcRequest implements Serializable {

	private static final long serialVersionUID = -4035618426165546997L;

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
		return "AncillarySvcRequest ["
				+ " originAirportCode : " + originAirportCode
				+ " destAirportCode : " + destAirportCode;
	}

}
