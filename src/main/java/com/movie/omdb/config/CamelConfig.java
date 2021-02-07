package com.movie.omdb.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet")
		.bindingMode(RestBindingMode.auto)
		.dataFormatProperty("json.out.disableFeatures", "FAIL_ON_EMPTY_BEANS")
		.apiContextPath("/swagger")
		.apiContextRouteId("swagger")
		.apiProperty("cors", "true")
		.apiProperty("api.title", "OMDB Search Api")
		.apiProperty("api.version", "1.0")
		.apiProperty("schemes", "http")
		.apiProperty("base.path", "/api")
		.apiProperty("consumes", "application/json, application/xml");

	}

}
