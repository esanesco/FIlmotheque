package fr.eni.dal;

import fr.eni.bo.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
