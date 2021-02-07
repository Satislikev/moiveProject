package com.movie.omdb.beans;

import static com.movie.omdb.constants.RouteEnum.HEADER_MOVIE_PLOT;
import static com.movie.omdb.constants.RouteEnum.HEADER_MOVIE_TITLE;
import static com.movie.omdb.constants.RouteEnum.HEADER_MOVIE_YEAR;

import javax.xml.bind.ValidationException;

import org.apache.camel.Exchange;
import org.apache.camel.TypeConversionException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.movie.omdb.constants.PlotEnum;

@Component
public class GetMoviesRequestValidation {
	public void validateRequest(Exchange exchange) throws Exception {
		// Title validations and adding as property
		String movieTitle = exchange.getIn().getHeader(HEADER_MOVIE_TITLE.getValue(), String.class);
		if (StringUtils.isBlank(movieTitle))
			throw new ValidationException("Movie name cannot be empty");
		
		// Plot validation and adding as property
		String moviePlot = exchange.getIn().getHeader(HEADER_MOVIE_PLOT.getValue(), String.class);
		if (StringUtils.isNoneBlank(moviePlot) && !StringUtils.equalsAnyIgnoreCase(moviePlot,
				PlotEnum.PLOT_FULL.getValue(), PlotEnum.PLOT_SHORT.getValue()))
			throw new ValidationException("Invalid Plot type.");
		
		// Year validation and adding as property
		try {
			Integer movieYear = exchange.getIn().getHeader(HEADER_MOVIE_YEAR.getValue(), Integer.class);
			if (null != movieYear && (movieYear > DateTime.now().getYear() || movieYear < 0))
				throw new ValidationException("Year value cannot be negative or in future.");
		} catch (TypeConversionException e) {
			throw new ValidationException("Invalid year parameter.");
		}
		
	}
}
