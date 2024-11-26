package alatoo.softwareEngineering.FilmVista.service.implementation;

import alatoo.softwareEngineering.FilmVista.exception.CustomException;
import alatoo.softwareEngineering.FilmVista.mapper.MovieMapper;
import alatoo.softwareEngineering.FilmVista.model.domain.Movie;
import alatoo.softwareEngineering.FilmVista.model.domain.Rating;
import alatoo.softwareEngineering.FilmVista.model.domain.User;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDetailResponse;
import alatoo.softwareEngineering.FilmVista.model.enums.Genre;
import alatoo.softwareEngineering.FilmVista.repository.*;
import alatoo.softwareEngineering.FilmVista.service.AuthService;
import alatoo.softwareEngineering.FilmVista.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final MovieRatingRepository movieRatingRepository;

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
        }if(user.getFavorites().contains(movie.get())){
            user.getFavorites().remove(movie.get());
        }else{
            user.getFavorites().add(movie.get());
        }
        userRepository.save(user);
        return user.getFavorites().contains(movie.get());
    }

    @Override
    public MovieDTO rate(String token, Long id, int rate) {
        User user = authService.getUserFromToken(token);
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isEmpty()){
            throw new CustomException("Movie with id: " + id + " - doesn't exist!" , HttpStatus.NOT_FOUND);
        }
        if (user == null) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        if (rate < 1 || rate > 10) {
            throw new CustomException("Rate must be between 1 and 10", HttpStatus.BAD_REQUEST);
        }
        Optional<Rating> existingRating = movieRatingRepository.findByUserAndMovie(user, movie.get());
        if(existingRating.isPresent()){
            Rating rating = existingRating.get();
            rating.setRating(rate);
            movieRatingRepository.save(rating);
        }else{
            Rating rating = new Rating();
            rating.setRating(rate);
            rating.setUser(user);
            rating.setMovie(movie.get());
            movieRatingRepository.save(rating);
        }
        return movieMapper.toDto(movie.get());
    }

    @Override
    public List<MovieDTO> findByGenre(String genre) {
        try {
            Genre genreEnum = Genre.valueOf(genre.toUpperCase());
            return movieRepository.findByGenre(genreEnum).stream()
                    .map(movieMapper::toDto)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new CustomException("Genre not found", HttpStatus.NOT_FOUND);
        }

    }


}
