/**
 * 
 */
package com.atanu.java.spring.offersvc.exception;

/**
 * @author Atanu Bhowmick
 *
 */
public class OfferSvcException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 394279177155816870L;

	private String errorCode;
	private String errorMsg;

	public OfferSvcException(Throwable e) {
		super(e);
	}

	public OfferSvcException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public OfferSvcException(String errorCode, String errorMsg, Throwable e) {
		super(e);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
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
}
