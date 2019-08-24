/**
 * 
 */
package com.atanu.java.spring.offersvc.client;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.atanu.java.spring.offersvc.constant.Constants;
import com.atanu.java.spring.offersvc.exception.OfferSvcException;
import com.atanu.java.spring.offersvc.model.client.PreferredAncillaryRequest;
import com.atanu.java.spring.offersvc.model.client.PreferredAncillaryResponse;

/**
 * @author Atanu Bhowmick
 *
 */
@Repository
public class DataSvcClient {

	/**
	 * @param request
	 * @return PreferredAncillaryResponse
	 * @throws OfferSvcException
	 */
	public PreferredAncillaryResponse getPreferredAncillaryResponseByAirports(PreferredAncillaryRequest request)
			throws OfferSvcException {

		PreferredAncillaryResponse response = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			response = restTemplate.postForObject(
					Constants.DATASVC_BASE_URL + Constants.DATASVC_URL_ANCILLARY + Constants.DATASVC_URL_GET_ANCILLARY,
					request, PreferredAncillaryResponse.class);
		} catch (RestClientException ex) {
			throw new OfferSvcException(Constants.ERROR_CODE_5003, Constants.ERROR_MSG_5003, ex);
		} catch (Exception ex) {
			throw new OfferSvcException(Constants.ERROR_CODE_5003, Constants.ERROR_MSG_5003, ex);
		}
		return response;
	}
}