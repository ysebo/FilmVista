package alatoo.softwareEngineering.FilmVista.model.dto.movie;
public record MovieDetailResponse (
     Long id,
     String title,
     String description,
     String genre,
     String director,
     int rating){
}
