/**
 * 
 */
package com.atanu.java.spring.offersvc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atanu.java.spring.offersvc.bo.AncillaryMgmtBO;
import com.atanu.java.spring.offersvc.constant.Constants;
import com.atanu.java.spring.offersvc.exception.OfferSvcException;
import com.atanu.java.spring.offersvc.logger.ApplicationLogger;
import com.atanu.java.spring.offersvc.model.FaultDO;
import com.atanu.java.spring.offersvc.model.service.Ancillary;
import com.atanu.java.spring.offersvc.model.service.AncillarySvcResponse;
import com.atanu.java.spring.offersvc.util.StringUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	
	@ApiOperation(value = "Get ancilarry by Id", response = Ancillary.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved the ancillary"),
			@ApiResponse(code = 404, message = "No Data Found", response = FaultDO.class),
			@ApiResponse(code = 500, message = Constants.ERROR_MSG_5004, response = FaultDO.class) 
	})
	@RequestMapping(value = "/{ancillaryId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Ancillary> getAncillary(
			@ApiParam(value = "Ancillary Id in path param", required = true)
			@PathVariable("ancillaryId") String ancillaryId)
			throws OfferSvcException {
		logger.debug("Inside getAncillary()");
		Ancillary ancillaryDetails = ancillaryMgmtBO.getAncillaryById(ancillaryId);
		return new ResponseEntity<>(ancillaryDetails, HttpStatus.OK);
	}

	
	@ApiOperation(value = "Get all ancilarries", response = List.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved the ancillary"),
			@ApiResponse(code = 404, message = "No Data Found", response = FaultDO.class),
			@ApiResponse(code = 500, message = Constants.ERROR_MSG_5004, response = FaultDO.class) 
	})
	@RequestMapping(value = Constants.PATH_GET_ALL_ANCILLARY, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Ancillary>> getAllAncillaries() throws OfferSvcException {
		logger.debug("Inside getAllAncillaries()");
		List<Ancillary> ancillaries = ancillaryMgmtBO.getAllAncillaries();
		return new ResponseEntity<>(ancillaries, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Get list of the avaliable ancilarries between two airports", response = AncillarySvcResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved ancillary list"),
			@ApiResponse(code = 400, message = Constants.ERROR_MSG_5001, response = FaultDO.class),
			@ApiResponse(code = 404, message = "No Data Found", response = FaultDO.class),
			@ApiResponse(code = 500, message = Constants.ERROR_MSG_5004, response = FaultDO.class) })
	@RequestMapping(value = Constants.PATH_GET_ANCILLARY, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AncillarySvcResponse> getAllAncillary(
			@ApiParam(value = "Send origin airport code in the query param", required = true) @RequestParam(required = true) String originAirportCode,
			@ApiParam(value = "Send destination airport code in the query param", required = true) @RequestParam(required = true) String destAirporCode)
			throws OfferSvcException {

		logger.debug("Inside getAllAncillary()");
		logger.debug("originAirportCode : {} , destAirporCode : {}", originAirportCode, destAirporCode);
		AncillarySvcResponse response = null;

		if (StringUtils.isEmpty(originAirportCode) || StringUtils.isEmpty(destAirporCode)) {
			logger.debug("Invalid request. Origin/Destination airport code is empty");
			throw new OfferSvcException(Constants.ERROR_CODE_5001, Constants.ERROR_MSG_5001, HttpStatus.BAD_REQUEST);
		} else {
			response = ancillaryMgmtBO.getAncillariesByAirorts(originAirportCode, destAirporCode);
			response.setOriginAirportCode(originAirportCode);
			response.setDestAirportCode(originAirportCode);
		}
		return new ResponseEntity<AncillarySvcResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hazelcast/save", method = RequestMethod.POST, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> getHazelCastData() {
		return new ResponseEntity<>(ancillaryMgmtBO.putInHazelastMap(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hazelcast/get", method = RequestMethod.GET, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> getFromHazelcastMap() {
		return new ResponseEntity<>(ancillaryMgmtBO.getFromHazelcastMap(), HttpStatus.OK);
	}
}
