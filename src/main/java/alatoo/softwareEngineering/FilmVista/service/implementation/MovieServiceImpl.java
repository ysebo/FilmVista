package alatoo.softwareEngineering.FilmVista.service.implementation;

import alatoo.softwareEngineering.FilmVista.exception.CustomException;
import alatoo.softwareEngineering.FilmVista.mapper.MovieMapper;
import alatoo.softwareEngineering.FilmVista.model.domain.Movie;
import alatoo.softwareEngineering.FilmVista.model.domain.User;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDetailResponse;
import alatoo.softwareEngineering.FilmVista.repository.MovieRepository;
import alatoo.softwareEngineering.FilmVista.service.AuthService;
import alatoo.softwareEngineering.FilmVista.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;
    private final AuthService authService;
    @Override
    public Set<MovieDTO> getAll() {
        return movieRepository.findAll().stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public MovieDTO getById(Long id) {
        Optional<Movie> movie =movieRepository.findById(id);
        if(movie.isEmpty()){
            throw new CustomException("Movie with id: " + id + " - doesn't exist!" , HttpStatus.NOT_FOUND);
        }
        return movieMapper.toDto(movie.get());
    }

    @Override
    public Boolean favorite(String token, Long id) {
        User user = authService.getUserFromToken(token);
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isEmpty()){
            throw new CustomException("Movie with id: " + id + " - doesn't exist!" , HttpStatus.NOT_FOUND);
        }
        if(user.getMovies().contains(movie.get())) {
            user.getMovies().remove(movie.get());
        }
        //TO DO there
        return null;
    }


}
