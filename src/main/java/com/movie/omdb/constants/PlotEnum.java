package com.movie.omdb.constants;

public enum PlotEnum {
	PLOT_SHORT("short"), PLOT_FULL("full");

	private final String value;

	private PlotEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
