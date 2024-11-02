package alatoo.softwareEngineering.FilmVista.service;

import alatoo.softwareEngineering.FilmVista.model.domain.User;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.AuthRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.AuthResponse;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.LoginRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.UserDTO;

public interface AuthService {
    UserDTO register(AuthRequest request);
    AuthResponse login(LoginRequest request);
    User getUserFromToken(String token);
}
