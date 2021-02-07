package com.movie.omdb.constants;

public enum RouteEnum {
	HEADER_MOVIE_TITLE("title"), HEADER_MOVIE_YEAR("year"), HEADER_MOVIE_PLOT("plot");

	private final String value;

	private RouteEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
