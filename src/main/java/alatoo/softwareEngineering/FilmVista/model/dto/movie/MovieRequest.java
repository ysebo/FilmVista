package alatoo.softwareEngineering.FilmVista.model.dto.movie;

import lombok.Data;
@Data
public class    MovieRequest {
    private String title;
    private String description;
    private String genre;
    private String director;
}
