package alatoo.softwareEngineering.FilmVista.service;

import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;

import java.util.Set;

public interface MovieService {
    Set<MovieDTO> getAll();

    MovieDTO getById(Long id);
    Boolean favorite(String token , Long id);

}
