
package com.movie.omdb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Title",
    "Year",
    "Rated",
    "Released",
    "Runtime",
    "Genre",
    "Director",
    "Writer",
    "Actors",
    "Plot",
    "Language",
    "Country",
    "Awards",
    "Poster",
    "Ratings",
    "Metascore",
    "imdbRating",
    "imdbVotes",
    "imdbID",
    "Type",
    "DVD",
    "BoxOffice",
    "Production",
    "Website",
    "Response",
    "Error"
})
@XmlRootElement(name = "Movie")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class GetMoviesResponse {

    @JsonProperty("Title")
    @XmlElement
    private String title;
    @JsonProperty("Year")
    @XmlElement
    private String year;
    @JsonProperty("Rated")
    @XmlElement
    private String rated;
    @JsonProperty("Released")
    @XmlElement
    private String released;
    @JsonProperty("Runtime")
    @XmlElement
    private String runtime;
    @JsonProperty("Genre")
    @XmlElement
    private String genre;
    @JsonProperty("Director")
    @XmlElement
    private String director;
    @JsonProperty("Writer")
    @XmlElement
    private String writer;
    @JsonProperty("Actors")
    @XmlElement
    private String actors;
    @JsonProperty("Plot")
    @XmlElement
    private String plot;
    @JsonProperty("Language")
    @XmlElement
    private String language;
    @JsonProperty("Country")
    @XmlElement
    private String country;
    @JsonProperty("Awards")
    @XmlElement
    private String awards;
    @JsonProperty("Poster")
    @XmlElement
    private String poster;
    @JsonProperty("Ratings")
    private List<Rating> ratings = null;
    @JsonProperty("Metascore")
    @XmlElement
    private String metascore;
    @JsonProperty("imdbRating")
    @XmlElement
    private String imdbRating;
    @JsonProperty("imdbVotes")
    @XmlElement
    private String imdbVotes;
    @JsonProperty("imdbID")
    @XmlElement
    private String imdbID;
    @JsonProperty("Type")
    @XmlElement
    private String type;
    @JsonProperty("DVD")
    @XmlElement
    private String dVD;
    @JsonProperty("BoxOffice")
    @XmlElement
    private String boxOffice;
    @JsonProperty("Production")
    @XmlElement
    private String production;
    @JsonProperty("Website")
    @XmlElement
    private String website;
    @JsonProperty("Response")
    @XmlElement
    private String response;
    @JsonProperty("Error")
    @XmlElement
    private String error;

}
