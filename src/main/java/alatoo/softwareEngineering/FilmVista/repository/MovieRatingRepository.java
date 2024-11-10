package alatoo.softwareEngineering.FilmVista.repository;

import alatoo.softwareEngineering.FilmVista.model.domain.Movie;
import alatoo.softwareEngineering.FilmVista.model.domain.Rating;
import alatoo.softwareEngineering.FilmVista.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserAndMovie(User user, Movie movie);
}