package com.movie.omdb.beans;

import static com.movie.omdb.constants.RouteEnum.HEADER_MOVIE_PLOT;
import static com.movie.omdb.constants.RouteEnum.HEADER_MOVIE_TITLE;
import static com.movie.omdb.constants.RouteEnum.HEADER_MOVIE_YEAR;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PrepareOmdbCall {

	@Value("${omdb.host}")
	private String omdbHost;

	@Value("${omdb.api-key}")
	private String omdbApiKey;

	public void prepareHttpRequest(Exchange exchange) throws Exception {
		exchange.getIn().removeHeaders("*", HEADER_MOVIE_PLOT.getValue(), HEADER_MOVIE_TITLE.getValue(),
				HEADER_MOVIE_YEAR.getValue(), "accept");
		exchange.getIn().setHeader(Exchange.HTTP_URI, omdbHost);
		exchange.getIn().setHeader(Exchange.HTTP_QUERY, buildQueryString(exchange));

	}

	private String buildQueryString(Exchange exchange) {
		String movieTitle = exchange.getIn().getHeader(HEADER_MOVIE_TITLE.getValue(), String.class);
		String moviePlot = exchange.getIn().getHeader(HEADER_MOVIE_PLOT.getValue(), String.class);
		String movieYear = exchange.getIn().getHeader(HEADER_MOVIE_YEAR.getValue(), String.class);
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("t=%s", movieTitle));

		if (StringUtils.isNoneBlank(moviePlot))
			builder.append(String.format("&plot=%s", moviePlot));

		if (StringUtils.isNoneBlank(movieYear))
			builder.append(String.format("&y=%s", movieYear));

		builder.append(String.format("&apikey=%s", omdbApiKey));

		return builder.toString();
	}
}
