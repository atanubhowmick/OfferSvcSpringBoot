/**
 * 
 */
package com.atanu.java.spring.offersvc.model;

import java.io.Serializable;

/**
 * @author Atanu Bhowmick
 *
 */
public class FaultDO implements Serializable {
	
	private static final long serialVersionUID = 6346004910126077137L;
	
	private String status;
	private ErrorDO error;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ErrorDO getError() {
		return error;
	}

	public void setError(ErrorDO error) {
		this.error = error;
	}

}
