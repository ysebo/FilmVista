package alatoo.softwareEngineering.FilmVista.model.dto.movie;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MovieDTO (
    Long id ,
    String title ,
    String description ,
    String director ,
    String genre ,
    int rating
){
}
