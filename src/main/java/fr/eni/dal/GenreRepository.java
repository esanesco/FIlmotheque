package fr.eni.dal;

import fr.eni.bo.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
