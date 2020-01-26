/**
 * 
 */
package com.atanu.java.spring.offersvc.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.springframework.http.HttpStatus;

import com.atanu.java.spring.offersvc.constant.Constants;
import com.atanu.java.spring.offersvc.exception.OfferSvcException;
import com.atanu.java.spring.offersvc.logger.ApplicationLogger;
import com.atanu.java.spring.offersvc.model.FaultDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @author Atanu Bhowmick
 *
 */
//@Component
public class FeignErrorDecoder implements ErrorDecoder {

	private static final ApplicationLogger logger = new ApplicationLogger(FeignErrorDecoder.class);

	@Override
	public Exception decode(String methodKey, Response response) {
		OfferSvcException exception = null;
		if (response != null && response.body() != null) {
			try {
				InputStream stream = response.body().asInputStream();
				String errorString = CharStreams.toString(new InputStreamReader(stream, Charset.defaultCharset()));
				logger.debug("Error from data-svc : {}", errorString);
				ObjectMapper mapper = new ObjectMapper();
				FaultDO fault = mapper.convertValue(errorString, FaultDO.class);
				exception = new OfferSvcException(fault.getError().getErrorCode(), fault.getError().getErrorMsg(),
						HttpStatus.valueOf(response.status()));
			} catch (IOException e) {
				exception = new OfferSvcException(Constants.ERROR_CODE_5003, Constants.ERROR_MSG_5003,
						HttpStatus.INTERNAL_SERVER_ERROR, e);
			}
		}
		return exception;
	}
}
