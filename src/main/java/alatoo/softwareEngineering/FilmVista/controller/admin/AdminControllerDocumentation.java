package alatoo.softwareEngineering.FilmVista.controller.admin;

import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Tag(name = "Admin", description = "Admin operations")
public sealed interface AdminControllerDocumentation permits AdminController  {
    @PostMapping("/movie/add")
    @Operation(summary = "Add movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403" , description = "No permission to add movie")
    })
    ResponseEntity<MovieDTO> add(HttpServletRequest httpServletRequest , @RequestBody MovieRequest request);

}
