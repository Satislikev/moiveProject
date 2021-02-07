package com.movie.omdb.beans;

import javax.xml.bind.ValidationException;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.movie.omdb.model.ErrorResponse;

import javassist.NotFoundException;

@Component
public class ErrorHandler {
	public void handleError(Exchange exchange) throws Exception {

		Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
		if(cause instanceof ValidationException) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(cause.getMessage());
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
			exchange.getIn().setBody(error);
			return;
		}
		
		if(cause instanceof NotFoundException) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(cause.getMessage());
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 404);
			exchange.getIn().setBody(error);
			return;
		}
		
		if(cause instanceof Exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(cause.getMessage());
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
			exchange.getIn().setBody(error);
			return;
		}
		
	}
}
