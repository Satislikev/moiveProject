package com.movie.omdb.beans;

import javax.xml.bind.ValidationException;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.movie.omdb.model.GetMoviesResponse;

import javassist.NotFoundException;

@Component
public class GetMoviesResponseValidation {
	public void validateResponse(Exchange exchange) throws Exception {

		GetMoviesResponse response = exchange.getIn().getBody(GetMoviesResponse.class);
		if (response.getResponse().equalsIgnoreCase("false")) {
			if (response.getError().equalsIgnoreCase("Movie not found!"))
				throw new NotFoundException("Movie could not be found.");
			else
				throw new ValidationException(response.getError());

		}

	}
}
