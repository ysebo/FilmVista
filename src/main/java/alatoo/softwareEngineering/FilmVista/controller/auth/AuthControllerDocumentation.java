package alatoo.softwareEngineering.FilmVista.controller.auth;

import alatoo.softwareEngineering.FilmVista.model.dto.auth.AuthRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.AuthResponse;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.LoginRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
@Tag(name = "Auth API", description = "This controller is responsible for registration and login")
public sealed interface AuthControllerDocumentation permits AuthController {
    @PostMapping("/register")
    @Operation(summary = "Register" , description = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
    })
    ResponseEntity<UserDTO> register(@Valid @RequestBody AuthRequest request);

    @PostMapping("/login")
    @Operation(summary = "Login" , description = "Login to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request);
}
