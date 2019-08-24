/**
 * 
 */
package com.atanu.java.spring.offersvc.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Atanu Bhowmick
 *
 */
public class OfferSvcException extends Exception {

	private static final long serialVersionUID = -9220031603273561297L;

	private String errorCode;
	private String errorMsg;
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	/*
	 * public OfferSvcException(Throwable e) { super(e); }
	 */

	public OfferSvcException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public OfferSvcException(String errorCode, String errorMsg, Throwable e) {
		super(e);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public OfferSvcException(String errorCode, String errorMsg, HttpStatus httpStatus) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.httpStatus = httpStatus;
	}

	public OfferSvcException(String errorCode, String errorMsg, HttpStatus httpStatus, Throwable e) {
		super(e);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.httpStatus = httpStatus;
	}

	@Override
	public String getMessage() {
		if (null != this.errorMsg) {
			return this.errorMsg;
		} else {
			return super.getMessage();
		}
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
