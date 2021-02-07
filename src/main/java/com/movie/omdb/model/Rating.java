
package com.movie.omdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Source",
    "Value"
})
@Data
public class Rating {

    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String value;

}
