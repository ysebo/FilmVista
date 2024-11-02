package alatoo.softwareEngineering.FilmVista.controller.movie;

import alatoo.softwareEngineering.FilmVista.model.dto.movie.MovieDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RequestMapping("/api/v1/movie")
@Tag(name = "Movie API", description = "Operations related to movies")
public sealed interface MovieControllerDocumentation permits MovieController {
    @GetMapping("/all")
    @Operation(summary = "Get all movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Movies not found")
    })
    ResponseEntity<Set<MovieDTO>> all();

    @GetMapping("/id/{id}")
    @Operation(summary = "Get movie by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    ResponseEntity<MovieDTO> getById(@Parameter(description = "Unique identifier of movie" , required = true) @PathVariable Long id);

    @PostMapping("/favorite/{id}")
    @Operation(summary = "Add movie to favorite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie added to favorite successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    Boolean favorite(HttpServletRequest httpServletRequest , @Parameter(description = "Unique identifier of movie" , required = true) @PathVariable Long id);
}
