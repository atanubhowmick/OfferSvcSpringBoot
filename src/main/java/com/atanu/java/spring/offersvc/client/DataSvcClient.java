/**
 * 
 */
package com.atanu.java.spring.offersvc.client;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.atanu.java.spring.offersvc.constant.Constants;
import com.atanu.java.spring.offersvc.exception.OfferSvcException;
import com.atanu.java.spring.offersvc.logger.ApplicationLogger;
import com.atanu.java.spring.offersvc.model.FaultDO;
import com.atanu.java.spring.offersvc.model.client.AncillaryDetails;
import com.atanu.java.spring.offersvc.model.client.PreferredAncillaryResponse;
import com.atanu.java.spring.offersvc.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Atanu Bhowmick
 *
 */
@Repository
public class DataSvcClient {

	private static final ApplicationLogger logger = new ApplicationLogger(DataSvcClient.class);

	public AncillaryDetails getAncillaryById(String ancillaryId) throws OfferSvcException {
		logger.debug("Inside DataSvcClient.getAncillaryById()");
		AncillaryDetails response = null;
		RestTemplate restTemplate = new RestTemplate();
		URI uri = UriComponentsBuilder
				.fromHttpUrl(Constants.DATASVC_BASE_URL + Constants.DATASVC_URL_ANCILLARY + "/" + ancillaryId).build()
				.toUri();
		HttpHeaders httpHeaders = new HttpHeaders();
		// httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(httpHeaders);
		try {
			ResponseEntity<AncillaryDetails> entity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
					AncillaryDetails.class);
			response = entity.getBody();
		} catch (HttpStatusCodeException ex) {
			handleHttpStatusCodeException(ex);
		} catch (Exception ex) {
			throw new OfferSvcException(Constants.ERROR_CODE_5004, Constants.ERROR_MSG_5004,
					HttpStatus.INTERNAL_SERVER_ERROR, ex);
		}
		return response;
	}

	public List<AncillaryDetails> getAllAncillaries() throws OfferSvcException {
		logger.debug("Inside DataSvcClient.getAncillaryById()");
		List<AncillaryDetails> response = null;
		RestTemplate restTemplate = new RestTemplate();
		URI uri = UriComponentsBuilder.fromHttpUrl(
				Constants.DATASVC_BASE_URL + Constants.DATASVC_URL_ANCILLARY + Constants.DATASVC_URL_GET_ALL_ANCILLARY)
				.build().toUri();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(httpHeaders);
		try {
			ResponseEntity<List<AncillaryDetails>> entity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<List<AncillaryDetails>>() {});
			response = entity.getBody();
		} catch (HttpStatusCodeException ex) {
			handleHttpStatusCodeException(ex);
		} catch (Exception ex) {
			throw new OfferSvcException(Constants.ERROR_CODE_5004, Constants.ERROR_MSG_5004,
					HttpStatus.INTERNAL_SERVER_ERROR, ex);
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
		RestTemplate restTemplate = new RestTemplate();
		URI uri = UriComponentsBuilder
				.fromHttpUrl(Constants.DATASVC_BASE_URL + Constants.DATASVC_URL_ANCILLARY
						+ Constants.DATASVC_URL_GET_ANCILLARY)
				.queryParam("originAirportCode", originAirportCode).queryParam("destAirporCode", destAirporCode).build()
				.toUri();
		HttpHeaders httpHeaders = new HttpHeaders();
		// httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(httpHeaders);
		try {
			ResponseEntity<PreferredAncillaryResponse> entity = restTemplate.exchange(uri, HttpMethod.GET,
					requestEntity, PreferredAncillaryResponse.class);
			response = entity.getBody();
		} catch (HttpStatusCodeException ex) {
			handleHttpStatusCodeException(ex);
		} catch (Exception ex) {
			throw new OfferSvcException(Constants.ERROR_CODE_5004, Constants.ERROR_MSG_5004,
					HttpStatus.INTERNAL_SERVER_ERROR, ex);
		}
		return response;
	}

	/**
	 * @param ex
	 * @throws OfferSvcException
	 */
	private void handleHttpStatusCodeException(HttpStatusCodeException ex) throws OfferSvcException {
		logger.debug("Inside handleHttpStatusCodeException()");
		String errorResponseBody = ex.getResponseBodyAsString();
		logger.debug("Https Status : {}", ex.getStatusCode());
		logger.debug("Error response : {}", errorResponseBody);

		String errorCode = Constants.ERROR_CODE_5003;
		String errorMsg = Constants.ERROR_MSG_5003;
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (StringUtils.isNotEmpty(errorResponseBody)) {
				FaultDO faultDO = mapper.readValue(errorResponseBody, FaultDO.class);
				errorCode = faultDO.getError().getErrorCode();
				errorMsg = faultDO.getError().getErrorMsg();
				httpStatus = ex.getStatusCode();
			}
		} catch (IOException e) {
			throw new OfferSvcException(Constants.ERROR_CODE_5004, Constants.ERROR_MSG_5004,
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		throw new OfferSvcException(errorCode, errorMsg, httpStatus, ex);
	}
}