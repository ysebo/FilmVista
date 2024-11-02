package alatoo.softwareEngineering.FilmVista.model.dto.auth;

import jakarta.validation.constraints.NotBlank;


public record AuthRequest (
    @NotBlank(message = "Username cannot be empty ")
    String username,
    @NotBlank(message = "Email cannot be empty")
    String email,
    @NotBlank(message = "Password cannot be empty or consist ony of spaces")
    String password
){}
