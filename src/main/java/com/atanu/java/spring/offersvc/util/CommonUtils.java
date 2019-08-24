/**
 * 
 */
package com.atanu.java.spring.offersvc.util;

import com.atanu.java.spring.offersvc.constant.Constants;
import com.atanu.java.spring.offersvc.model.ErrorDO;
import com.atanu.java.spring.offersvc.model.FaultDO;

/**
 * @author Atanu Bhowmick
 *
 */
public class CommonUtils {
	
	/**
	 * @return FaultDO
	 */
	public static FaultDO createFaultDOForSuccess(){
		FaultDO faultDO = new FaultDO();
		faultDO.setStatus(Constants.SUCCESS);
		return faultDO;
	}
	
	/**
	 * @param errorCode
	 * @param errorMsg
	 * @return FaultDO
	 */
	public static FaultDO createFaultDOForError(String errorCode, String errorMsg){
		FaultDO faultDO = new FaultDO();
		ErrorDO errorDO = new ErrorDO();
		errorDO.setErrorCode(errorCode);
		errorDO.setErrorMsg(errorMsg);
		faultDO.setStatus(Constants.ERROR);
		faultDO.setError(errorDO);
		return faultDO;
	}
}
