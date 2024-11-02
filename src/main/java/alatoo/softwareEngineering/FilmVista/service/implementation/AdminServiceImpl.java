package alatoo.softwareEngineering.FilmVista.service.implementation;

import alatoo.softwareEngineering.FilmVista.exception.CustomException;
import alatoo.softwareEngineering.FilmVista.mapper.MovieMapper;
import alatoo.softwareEngineering.FilmVista.model.domain.Movie;
import alatoo.softwareEngineering.FilmVista.model.domain.User;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieRequest;
import alatoo.softwareEngineering.FilmVista.repository.MovieRepository;
import alatoo.softwareEngineering.FilmVista.service.AdminService;
import alatoo.softwareEngineering.FilmVista.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AuthService authService;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    @Override
    public MovieDTO add(String token, MovieRequest request) {
        User user = authService.getUserFromToken(token);

        if(user == null){
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        if(movieRepository.findByTitle(request.getTitle()).isPresent()){
            throw new CustomException("Movie with title: " + request.getTitle() + " - already exist!" , HttpStatus.BAD_REQUEST);
        }
        Movie movie = new Movie();
        return movieMapper.toDto(movieRepository.save(movieMapper.toEntity(request)));
    }
}
