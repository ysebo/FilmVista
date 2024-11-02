package alatoo.softwareEngineering.FilmVista.controller.admin;

import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieRequest;
import alatoo.softwareEngineering.FilmVista.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class AdminController implements AdminControllerDocumentation {
    AdminService adminService;
    public ResponseEntity<MovieDTO> add(HttpServletRequest httpServletRequest , @RequestBody MovieRequest request){
        return  new ResponseEntity<MovieDTO> (adminService.add(httpServletRequest.getHeader("Authorization") , request) , HttpStatus.OK);
    }
}
