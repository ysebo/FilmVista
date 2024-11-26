package alatoo.softwareEngineering.FilmVista.repository;

import alatoo.softwareEngineering.FilmVista.model.domain.Movie;
import alatoo.softwareEngineering.FilmVista.model.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
    List<Movie> findByGenre(Genre genre);
}
