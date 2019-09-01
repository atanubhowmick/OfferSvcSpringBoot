package com.atanu.java.spring.offersvc.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atanu.java.spring.offersvc.constant.Constants;
import com.atanu.java.spring.offersvc.model.client.AncillaryDetails;
import com.atanu.java.spring.offersvc.model.client.PreferredAncillaryResponse;

/**
 * @author Atanu Bhowmick
 *
 */
@FeignClient("data-svc")
public interface DataSvcFeignClient {

	@RequestMapping(value = Constants.DATASVC_URL_ANCILLARY
			+ "/{ancillaryId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AncillaryDetails getAncillaryById(@PathVariable(value = "ancillaryId") String ancillaryId);

	@RequestMapping(value = Constants.DATASVC_URL_GET_ALL_ANCILLARY, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<AncillaryDetails> getAllAncillaries();

	@RequestMapping(value = Constants.DATASVC_URL_GET_ANCILLARY, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PreferredAncillaryResponse getPreferredAncillaryResponseByAirports(
			@RequestParam(value = "originAirportCode") String originAirportCode,
			@RequestParam(value = "destAirporCode") String destAirporCode);
}
