package fr.eni.service;

import fr.eni.bo.Member;
import fr.eni.dal.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionServiceImpl implements ConnectionService{
    private MemberRepository memberRepository;

    @Autowired
    public ConnectionServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member login(String email, String password) {
        if(email != null  && password != null) {
            return memberRepository.findByLoginAndPassword(email, password);
        }
        return null;
    }
}
