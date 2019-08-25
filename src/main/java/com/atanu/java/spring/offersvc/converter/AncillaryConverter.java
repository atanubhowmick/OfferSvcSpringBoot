package com.atanu.java.spring.offersvc.converter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.atanu.java.spring.offersvc.model.client.AncillaryDetails;
import com.atanu.java.spring.offersvc.model.client.PreferredAncillaryResponse;
import com.atanu.java.spring.offersvc.model.service.Ancillary;
import com.atanu.java.spring.offersvc.model.service.AncillarySvcResponse;

/**
 * @author Atanu Bhowmick
 *
 */
@Component
public class AncillaryConverter {

	/**
	 * @param ancillaryDetails
	 * @return Ancillary
	 */
	public Ancillary convertToAncllary(AncillaryDetails ancillaryDetails) {
		Ancillary ancillary = null;
		if (null != ancillaryDetails) {
			ancillary = new Ancillary(ancillaryDetails.getAncillaryId(), ancillaryDetails.getAncillaryName(),
					ancillaryDetails.getAncillaryDesc());
		}
		return ancillary;
	}

	/**
	 * @param preferredAncillaryResponse
	 * @return AncillarySvcResponse
	 */
	public AncillarySvcResponse covertToAncillarySvcResponse(PreferredAncillaryResponse preferredAncillaryResponse) {

		AncillarySvcResponse response = null;

		if (null != preferredAncillaryResponse) {
			response = new AncillarySvcResponse();
			response.setOriginAirportCode(preferredAncillaryResponse.getOriginAirportCode());
			response.setDestAirportCode(preferredAncillaryResponse.getDestAirportCode());

			List<AncillaryDetails> ancillaryDetailsList = preferredAncillaryResponse.getPreferredAncillaries();

			if (!CollectionUtils.isEmpty(ancillaryDetailsList)) {
				for (AncillaryDetails ancillaryDetails : ancillaryDetailsList) {
					response.getPreferredAncillaries().add(convertToAncllary(ancillaryDetails));
				}
			}
		}
		return response;
	}
}
