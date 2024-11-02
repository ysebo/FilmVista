package alatoo.softwareEngineering.FilmVista.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    String getUserEmailFromToken(String token);
}
