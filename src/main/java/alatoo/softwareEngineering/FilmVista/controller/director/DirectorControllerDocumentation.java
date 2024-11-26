package alatoo.softwareEngineering.FilmVista.controller.director;

import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/director")
@Tag(name = "Director", description = "Director operations")
public sealed interface DirectorControllerDocumentation permits DirectorController{
    @PostMapping("/movie/add")
    @Operation(summary = "Add movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403" , description = "No permission to add movie")
    })
    ResponseEntity<MovieDTO> add(HttpServletRequest httpServletRequest , @RequestBody MovieRequest request);
    @DeleteMapping("/movie/delete/{id}")
    @Operation(summary = "Delete movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403" , description = "No permission to delete movie")
    })
    void delete(HttpServletRequest httpServletRequest, @PathVariable Long id);
}
