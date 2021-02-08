package com.movie.omdb.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.movie.omdb.beans.ErrorHandler;
import com.movie.omdb.beans.GetMoviesRequestValidation;
import com.movie.omdb.beans.GetMoviesResponseValidation;
import com.movie.omdb.beans.PrepareOmdbCall;
import com.movie.omdb.model.GetMoviesResponse;

@Component
public class OmdbRoute extends RouteBuilder {

	private static final Logger logger = LoggerFactory.getLogger(OmdbRoute.class);

	@Override
	public void configure() throws Exception {
		omdbGetRoute();

	}

	private void omdbGetRoute() {
		
		
		rest()
			.get("/movies")
			.id("get-movies-rest-route")
			.param(RequestParameters.moviePlotParam())
			.param(RequestParameters.movieTitleParam())
			.param(RequestParameters.movieYearParam())
			.responseMessage(ResponseMessages.getMovie200())
			.responseMessage(ResponseMessages.internalError())
			.responseMessage(ResponseMessages.notFound())
			.responseMessage(ResponseMessages.validationError())
			.produces(MediaType.APPLICATION_JSON_VALUE + ", " + MediaType.APPLICATION_XML_VALUE)
			.outType(GetMoviesResponse.class)
			//Usually in bigger applications i will route to another route.
			.route().id("get-movies-system-route")
				.log(LoggingLevel.DEBUG,logger, "${in.headers}")
				.bean(GetMoviesRequestValidation.class, "validateRequest")
				.bean(PrepareOmdbCall.class, "prepareHttpRequest")
				.toD("http://host")
				.unmarshal().json(JsonLibrary.Jackson, GetMoviesResponse.class)
				.bean(GetMoviesResponseValidation.class, "validateResponse")
			.endRest();

		onException(Exception.class)
		.handled(true)
		.bean(ErrorHandler.class, "handleError");
	}

}
