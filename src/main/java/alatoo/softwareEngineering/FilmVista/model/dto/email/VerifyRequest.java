package alatoo.softwareEngineering.FilmVista.model.dto.email;

import jakarta.validation.constraints.NotBlank;

public record VerifyRequest(
        @NotBlank
        String email
) {
}
