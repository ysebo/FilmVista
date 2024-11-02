package alatoo.softwareEngineering.FilmVista.model.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTO(
    String username,
    String email,
    String role
) {
}
