/**
 * 
 */
package com.atanu.java.spring.offersvc.model.service;

import java.io.Serializable;

/**
 * @author Atanu Bhowmick
 *
 */
public class Ancillary implements Serializable {

	private static final long serialVersionUID = -3429123474397175306L;

	private String ancillaryId;
	private String ancillaryName;
	private String ancillaryDesc;

	public Ancillary() {
	}
	
	public Ancillary(String ancillaryId, String ancillaryName,
			String ancillaryDesc) {
		this.ancillaryId = ancillaryId;
		this.ancillaryName = ancillaryName;
		this.ancillaryDesc = ancillaryDesc;
	}

	public String getAncillaryId() {
		return ancillaryId;
	}

	public void setAncillaryId(String ancillaryId) {
		this.ancillaryId = ancillaryId;
	}

	public String getAncillaryName() {
		return ancillaryName;
	}

	public void setAncillaryName(String ancillaryName) {
		this.ancillaryName = ancillaryName;
	}

	public String getAncillaryDesc() {
		return ancillaryDesc;
	}

	public void setAncillaryDesc(String ancillaryDesc) {
		this.ancillaryDesc = ancillaryDesc;
	}
}
