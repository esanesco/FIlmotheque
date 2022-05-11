package fr.eni.dal;

import fr.eni.bo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByLoginAndPassword(String login, String password);
}
