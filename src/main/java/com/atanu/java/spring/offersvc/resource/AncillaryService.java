/**
 * 
 */
package com.atanu.java.spring.offersvc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atanu.java.spring.offersvc.bo.AncillaryMgmtBO;
import com.atanu.java.spring.offersvc.constant.Constants;
import com.atanu.java.spring.offersvc.exception.OfferSvcException;
import com.atanu.java.spring.offersvc.logger.ApplicationLogger;
import com.atanu.java.spring.offersvc.model.service.AncillarySvcRequest;
import com.atanu.java.spring.offersvc.model.service.AncillarySvcResponse;
import com.atanu.java.spring.offersvc.util.CommonUtils;
import com.atanu.java.spring.offersvc.util.StringUtils;

/**
 * @author Atanu Bhowmick
 *
 */

@RestController
@RequestMapping(value = "/ancillary")
public class AncillaryService {
	
	@Autowired
	private AncillaryMgmtBO ancillaryMgmtBO;
	
	private static final ApplicationLogger logger = new ApplicationLogger(AncillaryService.class);
	
	@RequestMapping(value = Constants.PATH_GET_ANCILLARY, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AncillarySvcResponse> getAllAncillary(@RequestBody AncillarySvcRequest ancillarySvcRequest){
		
		logger.debug("Inside getAllAncillary()");
		logger.debug("AncillarySvcRequest : {}", ancillarySvcRequest);
		AncillarySvcResponse response = null;
		
		if(ancillarySvcRequest == null || StringUtils.isEmpty(ancillarySvcRequest.getOriginAirportCode()) 
				|| StringUtils.isEmpty(ancillarySvcRequest.getDestAirportCode())) {
			logger.debug("Invalid Request... request : {}", ancillarySvcRequest);
			response = new AncillarySvcResponse();
			if(ancillarySvcRequest != null && ancillarySvcRequest.getOriginAirportCode() != null) {
				response.setOriginAirportCode(ancillarySvcRequest.getOriginAirportCode());
			}
			if(ancillarySvcRequest != null && ancillarySvcRequest.getDestAirportCode() != null) {
				response.setDestAirportCode(ancillarySvcRequest.getDestAirportCode());
			}
			response.setFault(CommonUtils.createFaultDOForError(Constants.ERROR_CODE_5001, Constants.ERROR_MSG_5001));
		} else {
			try {
				response = ancillaryMgmtBO.getAncillariesByAirorts(ancillarySvcRequest);
				if(null == response) {
					response = new AncillarySvcResponse();
					response.setOriginAirportCode(ancillarySvcRequest.getOriginAirportCode());
					response.setDestAirportCode(ancillarySvcRequest.getDestAirportCode());
					response.setFault(CommonUtils.createFaultDOForError(Constants.ERROR_CODE_5002, Constants.ERROR_MSG_5002));
				}
			} catch (OfferSvcException e) {
				response = new AncillarySvcResponse();
				response.setOriginAirportCode(ancillarySvcRequest.getOriginAirportCode());
				response.setDestAirportCode(ancillarySvcRequest.getDestAirportCode());
				response.setFault(CommonUtils.createFaultDOForError(e.getErrorCode(), e.getErrorMsg()));
				logger.error(e.getErrorMsg(), e);
			} catch (Exception e) {
				response = new AncillarySvcResponse();
				response.setOriginAirportCode(ancillarySvcRequest.getOriginAirportCode());
				response.setDestAirportCode(ancillarySvcRequest.getDestAirportCode());
				response.setFault(CommonUtils.createFaultDOForError(Constants.ERROR_CODE_5002, Constants.ERROR_MSG_5002));
				logger.error(e.getMessage(), e);
			}
		}
		
		return new ResponseEntity<AncillarySvcResponse>(response, HttpStatus.OK);
	}
}
