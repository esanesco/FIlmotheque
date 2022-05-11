package fr.eni.dal;

import fr.eni.bo.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    @Query("select o from Movie m join m.opinions o where m.id = ?1")
    List<Opinion> findOpinionsByMovie(Long idMovie);
}
