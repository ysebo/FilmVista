package alatoo.softwareEngineering.FilmVista.mapper;

import alatoo.softwareEngineering.FilmVista.model.domain.Movie;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDTO toDto(Movie movie);
    Movie toEntity(MovieRequest movieRequest);
}
