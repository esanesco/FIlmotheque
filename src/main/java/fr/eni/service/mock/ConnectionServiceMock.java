package fr.eni.service.mock;

import fr.eni.bo.Member;
import fr.eni.service.ConnectionService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionServiceMock implements ConnectionService {

    //Liste des membres
    private static List<Member> lstMembers;

    public ConnectionServiceMock() {
        //initialisation de la liste
        lstMembers= new ArrayList<>();
        lstMembers.add(new Member(1, "Baille", "Anne-Lise", "abaille@campus-eni.fr", "ass", true));
        lstMembers.add(new Member(2, "Gobin", "Stéphane", "sgobin@campus-eni.fr", "Pa$$w0rd", false));
        lstMembers.add(new Member(3, "Trillard", "Julien", "jtrillard@campus-eni.fr", "Pa$$w0rd", false));
    }

    @Override
    public Member login(String email, String password) {
        if(email != null && password != null) {
            for (Member member : lstMembers) {
                if(member.getLogin().equals(email) && member.getPassword().equals(password)) {
                    return new Member(member.getId(), member.getLastName(), member.getFirstName(),member.getLogin(), null, member.isAdmin());

                }
            }
        }
        return null;
    }
}
