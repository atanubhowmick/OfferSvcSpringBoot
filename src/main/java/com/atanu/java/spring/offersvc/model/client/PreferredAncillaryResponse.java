/**
 * 
 */
package com.atanu.java.spring.offersvc.model.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.atanu.java.spring.offersvc.model.FaultDO;

/**
 * @author Atanu Bhowmick
 *
 */

public class PreferredAncillaryResponse implements Serializable {

	private static final long serialVersionUID = -5730428775920387647L;

	private String originAirportCode;
	private String destAirportCode;
	private List<AncillaryDetails> preferredAncillaries;
	private FaultDO fault;

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

	public List<AncillaryDetails> getPreferredAncillaries() {
		if (null == preferredAncillaries) {
			preferredAncillaries = new ArrayList<AncillaryDetails>();
		}
		return preferredAncillaries;
	}

	public FaultDO getFault() {
		return fault;
	}

	public void setFault(FaultDO fault) {
		this.fault = fault;
	}

}
