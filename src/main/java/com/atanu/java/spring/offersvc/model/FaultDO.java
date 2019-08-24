/**
 * 
 */
package com.atanu.java.spring.offersvc.model;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ATANU
 *
 */
@ApiModel(value = "FaultDO", description = "Response status with error if any")
public class FaultDO implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6346004910126077137L;
	
	@ApiModelProperty(value = "Response status")
	private String status;
	
	@ApiModelProperty(value = "Error details")
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
