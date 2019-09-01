/**
 * 
 */
package com.atanu.java.spring.offersvc.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.atanu.java.spring.offersvc.constant.Constants;
import com.atanu.java.spring.offersvc.exception.OfferSvcException;
import com.atanu.java.spring.offersvc.logger.ApplicationLogger;
import com.atanu.java.spring.offersvc.model.FaultDO;
import com.atanu.java.spring.offersvc.model.client.AncillaryDetails;
import com.atanu.java.spring.offersvc.model.client.PreferredAncillaryResponse;
import com.atanu.java.spring.offersvc.util.StringUtils;
import com.google.gson.Gson;

import feign.FeignException;

/**
 * @author Atanu Bhowmick
 *
 */
@Repository
public class DataSvcClient {

	private static final ApplicationLogger logger = new ApplicationLogger(DataSvcClient.class);

	@Autowired
	private DataSvcFeignClient client;

	public AncillaryDetails getAncillaryById(String ancillaryId) throws OfferSvcException {
		logger.debug("Inside DataSvcClient.getAncillaryById()");
		AncillaryDetails response = null;
		try {
			response = client.getAncillaryById(ancillaryId);
		} catch (FeignException ex) {
			handleFeignException(ex);
		}
		return response;
	}

	public List<AncillaryDetails> getAllAncillaries() throws OfferSvcException {
		logger.debug("Inside DataSvcClient.getAncillaryById()");
		List<AncillaryDetails> response = null;
		try {
			response = client.getAllAncillaries();
		} catch (FeignException ex) {
			handleFeignException(ex);
		}
		return response;
	}

	/**
	 * @param request
	 * @return PreferredAncillaryResponse
	 * @throws OfferSvcException
	 */
	public PreferredAncillaryResponse getPreferredAncillaryResponseByAirports(String originAirportCode,
			String destAirporCode) throws OfferSvcException {
		logger.debug("Inside DataSvcClient.getPreferredAncillaryResponseByAirports()");
		PreferredAncillaryResponse response = null;
		try {
			response = client.getPreferredAncillaryResponseByAirports(originAirportCode, destAirporCode);
		} catch (FeignException ex) {
			handleFeignException(ex);
		}
		return response;
	}

	/**
	 * @param FeignException
	 * @throws OfferSvcException
	 */
	private void handleFeignException(FeignException ex) throws OfferSvcException {
		String errorString = ex.contentUTF8();
		if (StringUtils.isNotEmpty(errorString)) {
			logger.debug("Error from data-svc : {}", errorString);
			Gson gson = new Gson();
			FaultDO faultDO = gson.fromJson(errorString, FaultDO.class);
			throw new OfferSvcException(faultDO.getError().getErrorCode(), faultDO.getError().getErrorMsg(),
					HttpStatus.valueOf(ex.status()));
		} else {
			throw new OfferSvcException(Constants.ERROR_CODE_5003, Constants.ERROR_MSG_5003,
					HttpStatus.valueOf(ex.status()), ex);
		}
	}
}