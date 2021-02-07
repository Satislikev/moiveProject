package com.movie.omdb.route;

import org.apache.camel.model.rest.RestOperationResponseMsgDefinition;

import com.movie.omdb.model.ErrorResponse;
import com.movie.omdb.model.GetMoviesResponse;

public class ResponseMessages {
	
	public static RestOperationResponseMsgDefinition getMovie200() {
		return new RestOperationResponseMsgDefinition()
				.code(200)
				.responseModel(GetMoviesResponse.class);
	}
	
	public static RestOperationResponseMsgDefinition validationError() {
		return new RestOperationResponseMsgDefinition()
				.code(400)
				.message("Invalid user input")
				.responseModel(ErrorResponse.class);
	}
	
	public static RestOperationResponseMsgDefinition notFound() {
		return new RestOperationResponseMsgDefinition()
				.code(404)
				.message("Movie could not be found.")
				.responseModel(ErrorResponse.class);
	}
	
	public static RestOperationResponseMsgDefinition InternalError() {
		return new RestOperationResponseMsgDefinition()
				.code(500)
				.message("Internal error due to technical problems.")
				.responseModel(ErrorResponse.class);
	}
}
