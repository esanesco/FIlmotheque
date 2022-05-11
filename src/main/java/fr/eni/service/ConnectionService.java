package fr.eni.service;

import fr.eni.bo.Member;

public interface ConnectionService {

    Member login(String email, String password);
}
