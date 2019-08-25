/**
 * 
 */
package com.atanu.java.spring.offersvc.constant;

/**
 * @author Atanu Bhowmick
 *
 */
public class Constants {
	
	private Constants() {
	}

	public static final String SUCCESS							= "SUCCESS";
	public static final String ERROR							= "ERROR";
	
	public static final String PATH_GET_ANCILLARY				= "/getAncillary";
	public static final String PATH_GET_ALL_ANCILLARY 			= "/getAllAncillary";
	
	public static final String ERROR_CODE_5001					= "5001";
	public static final String ERROR_CODE_5002					= "5002";
	public static final String ERROR_CODE_5003					= "5003";
	public static final String ERROR_CODE_5004					= "5004";
	
	public static final String ERROR_MSG_5001					= "Origin/destination airport can't be empty.";
	public static final String ERROR_MSG_5002					= "No data found. Please try again later.";
	public static final String ERROR_MSG_5003					= "Unable to connect data svc.";
	public static final String ERROR_MSG_5004					= "Internal Server Error";
	
	public static final String DATASVC_BASE_URL 				= "http://localhost:8082/data-svc";
	public static final String DATASVC_URL_ANCILLARY			= "/ancillary";
	public static final String DATASVC_URL_GET_ANCILLARY		= "/getAncillaryByAirports";
	public static final String DATASVC_URL_GET_ALL_ANCILLARY	= "/getAllAncillary";
}