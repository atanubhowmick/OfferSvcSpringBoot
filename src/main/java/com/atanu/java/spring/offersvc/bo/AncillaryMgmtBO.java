/**
 * 
 */
package com.atanu.java.spring.offersvc.bo;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.atanu.java.spring.offersvc.client.DataSvcClient;
import com.atanu.java.spring.offersvc.converter.AncillaryConverter;
import com.atanu.java.spring.offersvc.exception.OfferSvcException;
import com.atanu.java.spring.offersvc.logger.ApplicationLogger;
import com.atanu.java.spring.offersvc.model.client.AncillaryDetails;
import com.atanu.java.spring.offersvc.model.client.PreferredAncillaryResponse;
import com.atanu.java.spring.offersvc.model.service.Ancillary;
import com.atanu.java.spring.offersvc.model.service.AncillarySvcResponse;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * @author Atanu Bhowmick
 *
 */
@Service
public class AncillaryMgmtBO {

	@Autowired
	private DataSvcClient dataSvcClient;
	
	@Autowired
	private AncillaryConverter converter;
	
	@Autowired
	private HazelcastInstance hazelcastInstance;

	private static final ApplicationLogger logger = new ApplicationLogger(AncillaryMgmtBO.class);

	/**
	 * @param ancillaryId
	 * @return Ancillary
	 * @throws OfferSvcException
	 */
	public Ancillary getAncillaryById(String ancillaryId) throws OfferSvcException {
		logger.debug("Inside getAncillaryDetailsById()");
		return converter.convertToAncllary(dataSvcClient.getAncillaryById(ancillaryId));
	}

	/**
	 * @return List<Ancillary>
	 * @throws OfferSvcException
	 */
	public List<Ancillary> getAllAncillaries() throws OfferSvcException {
		logger.debug("Inside getAncillaryDetailsById()");
		List<AncillaryDetails> ancillaryDetails = dataSvcClient.getAllAncillaries();
		List<Ancillary> ancillaries = null;
		if (!CollectionUtils.isEmpty(ancillaryDetails)) {
			ancillaries = ancillaryDetails.stream().map(details -> converter.convertToAncllary(details))
					.collect(Collectors.toList());
		}
		return ancillaries;
	}

	/**
	 * @param ancillarySvcRequest
	 * @return AncillarySvcResponse
	 * @throws OfferSvcException
	 */
	public AncillarySvcResponse getAncillariesByAirorts(String originAirportCode, String destAirporCode)
			throws OfferSvcException {

		logger.debug("Inside getAllAncillariesByAirortCode()");
		PreferredAncillaryResponse ancillaryResponse = dataSvcClient
				.getPreferredAncillaryResponseByAirports(originAirportCode, destAirporCode);
		return converter.covertToAncillarySvcResponse(ancillaryResponse);
	}
	
	public String putInHazelastMap() {
		IMap<String, String> iMap = hazelcastInstance.getMap("TEST_MAP");
		iMap.lock("TEST_KEY");
		iMap.put("TEST_KEY", "This is hazelcast cache testing..cache", 2L, TimeUnit.MINUTES);
		iMap.unlock("TEST_KEY");
		return "Success";
	}
	
	public String getFromHazelcastMap() {
		IMap<String, String> iMap = hazelcastInstance.getMap("TEST_MAP");
		return iMap.get("TEST_KEY");
	}

}