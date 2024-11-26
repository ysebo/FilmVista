package alatoo.softwareEngineering.FilmVista.controller.director;

import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieRequest;
import alatoo.softwareEngineering.FilmVista.service.DirectorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class DirectorController implements DirectorControllerDocumentation {
    DirectorService directorService;
    @Override
    public ResponseEntity<MovieDTO> add(HttpServletRequest httpServletRequest, MovieRequest request) {
        return  new ResponseEntity<MovieDTO> (directorService.add(httpServletRequest.getHeader("Authorization") , request) , HttpStatus.OK);
    }

    @Override
    public void delete(HttpServletRequest httpServletRequest , Long id ) {
        directorService.delete(httpServletRequest.getHeader("Authorization"), id);
    }
}
