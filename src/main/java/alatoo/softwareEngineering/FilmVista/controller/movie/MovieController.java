package alatoo.softwareEngineering.FilmVista.controller.movie;

import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDetailResponse;
import alatoo.softwareEngineering.FilmVista.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class MovieController implements MovieControllerDocumentation {
    MovieService movieService;
    public ResponseEntity<Set<MovieDTO>> all(){
        return ResponseEntity.ok(movieService.getAll());
    }
    public ResponseEntity<MovieDTO> getById( Long id){
        return ResponseEntity.ok(movieService.getById(id));
    }
    public Boolean favorite(HttpServletRequest request, Long id){
       return movieService.favorite(request.getHeader("Authorization"), id);
    }
    public ResponseEntity<MovieDTO> rate(HttpServletRequest request, Long id, int rate){
        return ResponseEntity.ok(movieService.rate(request.getHeader("Authorization"), id, rate));
    }
}
