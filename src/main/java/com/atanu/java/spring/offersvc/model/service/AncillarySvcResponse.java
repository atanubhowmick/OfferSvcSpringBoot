/**
 * 
 */
package com.atanu.java.spring.offersvc.model.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.atanu.java.spring.offersvc.model.FaultDO;

/**
 * @author ATANU
 *
 */
public class AncillarySvcResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -222397412301397474L;

	private String originAirportCode;
	private String destAirportCode;
	private List<Ancillary> suggestedAncillaries;
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

	public List<Ancillary> getSuggestedAncillaries() {
		if (null == suggestedAncillaries) {
			suggestedAncillaries = new ArrayList<Ancillary>();
		}
		return suggestedAncillaries;
	}

	public FaultDO getFault() {
		return fault;
	}

	public void setFault(FaultDO fault) {
		this.fault = fault;
	}

}