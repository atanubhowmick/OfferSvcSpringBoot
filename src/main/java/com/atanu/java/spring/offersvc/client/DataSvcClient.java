/**
 * 
 */
package com.atanu.java.spring.offersvc.client;

import java.net.URI;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.atanu.java.spring.offersvc.constant.Constants;
import com.atanu.java.spring.offersvc.exception.OfferSvcException;
import com.atanu.java.spring.offersvc.logger.ApplicationLogger;
import com.atanu.java.spring.offersvc.model.client.PreferredAncillaryRequest;
import com.atanu.java.spring.offersvc.model.client.PreferredAncillaryResponse;

/**
 * @author Atanu Bhowmick
 *
 */
@Repository
public class DataSvcClient {

	private static final ApplicationLogger logger = new ApplicationLogger(DataSvcClient.class);

	/**
	 * @param request
	 * @return PreferredAncillaryResponse
	 * @throws OfferSvcException
	 */
	public PreferredAncillaryResponse getPreferredAncillaryResponseByAirports(PreferredAncillaryRequest request)
			throws OfferSvcException {
		logger.debug("Inside DataSvcClient.getPreferredAncillaryResponseByAirports()");
		PreferredAncillaryResponse response = null;
		ResponseEntity<PreferredAncillaryResponse> entity = null;
		RestTemplate restTemplate = new RestTemplate();
		URI uri = UriComponentsBuilder
				.fromHttpUrl(Constants.DATASVC_BASE_URL + Constants.DATASVC_URL_ANCILLARY
						+ Constants.DATASVC_URL_GET_ANCILLARY)
				.queryParam("originAirportCode", request.getOriginAirportCode())
				.queryParam("destAirporCode", request.getDestAirportCode()).build().toUri();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(httpHeaders);
			entity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, PreferredAncillaryResponse.class);
			if (HttpStatus.OK.equals(entity.getStatusCode())) {
				response = entity.getBody();
			}
		} catch (RestClientException ex) {
			throw new OfferSvcException(Constants.ERROR_CODE_5003, Constants.ERROR_MSG_5003, ex);
		} catch (Exception ex) {
			throw new OfferSvcException(Constants.ERROR_CODE_5003, Constants.ERROR_MSG_5003, ex);
		}
		return response;
	}
}