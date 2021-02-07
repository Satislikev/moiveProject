package com.movie.omdb.route;

import static com.movie.omdb.constants.PlotEnum.*;
import static com.movie.omdb.constants.RouteEnum.*;

import org.apache.camel.model.rest.RestOperationParamDefinition;
import org.apache.camel.model.rest.RestParamType;

public class RequestParameters {
	
	public static RestOperationParamDefinition movieTitleParam() {
		return new RestOperationParamDefinition()
				.name(HEADER_MOVIE_TITLE.getValue())
				.type(RestParamType.query)
				.dataType("string")
				.description("Title of the movie")
				.example("Lord Of the Rings")
				.required(true);
	}
	
	public static RestOperationParamDefinition movieYearParam() {
		return new RestOperationParamDefinition()
				.name(HEADER_MOVIE_YEAR.getValue())
				.type(RestParamType.query)
				.dataType("string")
				.description("Year of the movie, String due to bug")
				.example("2018")
				.required(false);
	}
	
	public static RestOperationParamDefinition moviePlotParam() {
		return new RestOperationParamDefinition()
				.name(HEADER_MOVIE_PLOT.getValue())
				.type(RestParamType.query)
				.dataType("string")
				.allowableValues(PLOT_FULL.getValue(),PLOT_SHORT.getValue())
				.description("Plot of moview")
				.example("short")
				.required(false);
	}
}
