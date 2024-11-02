package alatoo.softwareEngineering.FilmVista.controller.auth;

import alatoo.softwareEngineering.FilmVista.model.dto.auth.AuthRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.AuthResponse;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.LoginRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.UserDTO;
import alatoo.softwareEngineering.FilmVista.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level= lombok.AccessLevel.PRIVATE, makeFinal = true)
public final class AuthController implements AuthControllerDocumentation {
    AuthService authService;
    @Override
    public ResponseEntity<UserDTO> register(AuthRequest loginRequest) {
        return new ResponseEntity <>(authService.register(loginRequest), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }
}
