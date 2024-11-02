package alatoo.softwareEngineering.FilmVista.service.implementation;

import alatoo.softwareEngineering.FilmVista.exception.CustomException;
import alatoo.softwareEngineering.FilmVista.mapper.UserMapper;
import alatoo.softwareEngineering.FilmVista.model.domain.User;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.AuthRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.AuthResponse;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.LoginRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.UserDTO;
import alatoo.softwareEngineering.FilmVista.model.enums.Role;
import alatoo.softwareEngineering.FilmVista.repository.UserRepository;
import alatoo.softwareEngineering.FilmVista.service.AuthService;
import alatoo.softwareEngineering.FilmVista.service.JwtService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper mapper;

    @Override
    public UserDTO register(AuthRequest request) {
        if(userRepository.findByUsername(request.username()).isPresent()){
            throw new CustomException("User with this username already exists!", HttpStatus.FOUND);
        }
        User user = mapper.toEntity(request);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.password()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new CustomException("Error while saving user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return mapper.toDto(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomException("User does not exists", HttpStatus.NOT_FOUND));
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        }catch (AuthenticationException e ){
            throw new CustomException("Invalid credentials!", HttpStatus.UNAUTHORIZED);
        }
        String token = jwtService.generateToken(user);
        return mapper.toLoginDtoToken(user, token);
    }

    @Override
    public User getUserFromToken(String token) {
        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        if (chunks.length != 3)
            throw new BadCredentialsException("Wrong token!");

        JSONParser jsonParser = new JSONParser();
        JSONObject object;
        try {
            byte[] decodedBytes = decoder.decode(chunks[1]);
            String jsonString = new String(decodedBytes, StandardCharsets.UTF_8);
            object = (JSONObject) jsonParser.parse(jsonString);
        } catch (ParseException e) {
            throw new BadCredentialsException("Wrong token!!");
        }
        return userRepository.findByEmail(String.valueOf(object.get("sub"))).orElseThrow(() ->
                new BadCredentialsException("Wrong token!!!"));
    }
}
