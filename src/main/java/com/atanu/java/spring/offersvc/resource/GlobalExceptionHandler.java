/**
 * 
 */
package com.atanu.java.spring.offersvc.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.atanu.java.spring.offersvc.constant.Constants;
import com.atanu.java.spring.offersvc.exception.OfferSvcException;
import com.atanu.java.spring.offersvc.logger.ApplicationLogger;
import com.atanu.java.spring.offersvc.model.FaultDO;
import com.atanu.java.spring.offersvc.util.CommonUtils;

/**
 * @author Atanu Bhowmick
 *
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final ApplicationLogger logger = new ApplicationLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(OfferSvcException.class)
	public ResponseEntity<FaultDO> handleDataSvcException(OfferSvcException ex) {
		logger.error("Handling DataSvcException... ", ex);
		FaultDO fault = CommonUtils.createFaultDOForError(ex.getErrorCode(), ex.getErrorMsg());
		return new ResponseEntity<>(fault, ex.getHttpStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<FaultDO> handleGenericException(Exception ex) {
		logger.error("Handling Exception... ", ex);
		FaultDO fault = CommonUtils.createFaultDOForError(Constants.ERROR_CODE_5004, Constants.ERROR_MSG_5004);
		return new ResponseEntity<>(fault, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
