package alatoo.softwareEngineering.FilmVista.repository;

import alatoo.softwareEngineering.FilmVista.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    Optional<User> findByVerificationToken(String token);

    Optional<User> findByCode(String code);
}
