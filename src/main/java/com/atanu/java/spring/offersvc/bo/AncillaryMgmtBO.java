/**
 * 
 */
package com.atanu.java.spring.offersvc.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atanu.java.spring.offersvc.client.DataSvcClient;
import com.atanu.java.spring.offersvc.exception.OfferSvcException;
import com.atanu.java.spring.offersvc.logger.ApplicationLogger;
import com.atanu.java.spring.offersvc.model.client.AncillaryDetails;
import com.atanu.java.spring.offersvc.model.client.PreferredAncillaryRequest;
import com.atanu.java.spring.offersvc.model.client.PreferredAncillaryResponse;
import com.atanu.java.spring.offersvc.model.service.Ancillary;
import com.atanu.java.spring.offersvc.model.service.AncillarySvcRequest;
import com.atanu.java.spring.offersvc.model.service.AncillarySvcResponse;

/**
 * @author Atanu Bhowmick
 *
 */
@Service
public class AncillaryMgmtBO {
	
	@Autowired
	private DataSvcClient dataSvcClient;
	
	private static final ApplicationLogger logger = new ApplicationLogger(AncillaryMgmtBO.class);
	
	/**
	 * @param ancillarySvcRequest
	 * @return AncillarySvcResponse
	 * @throws OfferSvcException 
	 */
	public AncillarySvcResponse getAncillariesByAirorts(AncillarySvcRequest ancillarySvcRequest) throws OfferSvcException {
		
		logger.debug("Inside getAllAncillariesByAirortCode()");
		
		PreferredAncillaryRequest request = new PreferredAncillaryRequest();
		request.setOriginAirportCode(ancillarySvcRequest.getOriginAirportCode());
		request.setDestAirportCode(ancillarySvcRequest.getDestAirportCode());
		
		PreferredAncillaryResponse ancillaryResponse = dataSvcClient.getPreferredAncillaryResponseByAirports(request);
		
		return createAncillarySvcResponse(ancillaryResponse);
	}
	
	/**
	 * @param preferredAncillaryResponse
	 * @return AncillarySvcResponse
	 */
	private AncillarySvcResponse createAncillarySvcResponse(PreferredAncillaryResponse preferredAncillaryResponse) {
		
		AncillarySvcResponse response = null;
		
		if (null != preferredAncillaryResponse) {
			response = new AncillarySvcResponse();
			response.setOriginAirportCode(preferredAncillaryResponse.getOriginAirportCode());
			response.setDestAirportCode(preferredAncillaryResponse.getDestAirportCode());
			response.setFault(preferredAncillaryResponse.getFault());
			
			List<AncillaryDetails> ancillaryDetailsList = preferredAncillaryResponse.getPreferredAncillaries();
			
			if (null != ancillaryDetailsList && !ancillaryDetailsList.isEmpty()) {
				for(AncillaryDetails ancillaryDetails : ancillaryDetailsList){
					Ancillary ancillary = new Ancillary();
					ancillary.setAncillaryId(ancillaryDetails.getAncillaryId());
					ancillary.setAncillaryName(ancillaryDetails.getAncillaryName());
					ancillary.setAncillaryDesc(ancillaryDetails.getAncillaryDesc());
					response.getSuggestedAncillaries().add(ancillary);
				}
			}
		}
		return response;
	}
}