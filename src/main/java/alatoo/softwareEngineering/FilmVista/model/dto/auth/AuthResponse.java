package alatoo.softwareEngineering.FilmVista.model.dto.auth;

public record AuthResponse(
        String username,
        String role,
        String token
        ) {
}
