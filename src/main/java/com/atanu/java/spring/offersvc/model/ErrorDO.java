/**
 * 
 */
package com.atanu.java.spring.offersvc.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Atanu Bhowmick
 *
 */
@ApiModel(value = "ErrorDO", description = "Error Details")
public class ErrorDO implements Serializable {

	private static final long serialVersionUID = 4947858162814897886L;

	@ApiModelProperty(value = "Error Code")
	private String errorCode;
	
	@ApiModelProperty(value = "Error Message")
	private String errorMsg;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
