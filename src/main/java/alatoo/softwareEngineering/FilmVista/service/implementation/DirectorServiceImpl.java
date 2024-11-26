package alatoo.softwareEngineering.FilmVista.service.implementation;

import alatoo.softwareEngineering.FilmVista.exception.CustomException;
import alatoo.softwareEngineering.FilmVista.mapper.MovieMapper;
import alatoo.softwareEngineering.FilmVista.model.domain.Movie;
import alatoo.softwareEngineering.FilmVista.model.domain.User;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieRequest;
import alatoo.softwareEngineering.FilmVista.repository.MovieRepository;
import alatoo.softwareEngineering.FilmVista.service.AuthService;
import alatoo.softwareEngineering.FilmVista.service.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {
    private AuthService authService;
    private MovieRepository movieRepository;
    private MovieMapper movieMapper;
    @Override
    public MovieDTO add(String token, MovieRequest request) {
        User user = authService.getUserFromToken(token);

        if(user == null){
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        if(movieRepository.findByTitle(request.getTitle()).isPresent()){
            throw new CustomException("Movie with title: " + request.getTitle() + " - already exist!" , HttpStatus.BAD_REQUEST);
        }
        return movieMapper.toDto(movieRepository.save(movieMapper.toEntity(request)));
    }

    @Override
    public void delete(String authorization, Long id) {
        User user = authService.getUserFromToken(authorization);
        if(user == null){
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new CustomException("Movie with id: " + id + " - doesn't exist!" , HttpStatus.NOT_FOUND));
        movieRepository.delete(movie);
    }
}
