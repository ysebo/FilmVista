package alatoo.softwareEngineering.FilmVista.service;

import org.springframework.security.core.userdetails.UserDetails;


public interface JwtService {
    String generateToken(UserDetails userDetails);
    String getUserEmailFromToken(String token);

}
