package alatoo.softwareEngineering.FilmVista.service;

import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieRequest;

public interface DirectorService {
    MovieDTO add(String token , MovieRequest request );
    void delete(String authorization, Long id );

}
