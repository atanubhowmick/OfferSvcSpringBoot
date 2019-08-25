/**
 * 
 */
package com.atanu.java.spring.offersvc.model.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Atanu Bhowmick
 *
 */
@ApiModel(value = "AncillarySvcResponse", description = "All details about Preferred Ancillary Response")
public class AncillarySvcResponse implements Serializable {

	private static final long serialVersionUID = 5281116808322753085L;

	@ApiModelProperty(value = "Origin Airport Code", example = "ATL")
	private String originAirportCode;

	@ApiModelProperty(value = "Destination Airport Code", example = "MSP")
	private String destAirportCode;

	@ApiModelProperty(value = "List of Preffered Ancillaries")
	private List<Ancillary> preferredAncillaries;

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

	public List<Ancillary> getPreferredAncillaries() {
		if (null == preferredAncillaries) {
			preferredAncillaries = new ArrayList<>();
		}
		return preferredAncillaries;
	}
}