package alatoo.softwareEngineering.FilmVista.mapper;

import alatoo.softwareEngineering.FilmVista.model.domain.User;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.AuthRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.AuthResponse;
import alatoo.softwareEngineering.FilmVista.model.dto.auth.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    AuthResponse toLoginResponseDTO(User user);
    default AuthResponse toLoginDtoToken(User user , String token){
        AuthResponse dto = toLoginResponseDTO(user);
        return new AuthResponse(dto.username() , dto.role() , token);
    }
    @Mapping(source = "username", target = "username")
    User toEntity(AuthRequest request);
}
