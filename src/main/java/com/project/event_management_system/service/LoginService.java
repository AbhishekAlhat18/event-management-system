package com.project.event_management_system.service;

import com.project.event_management_system.model.AuthenticationResponse;
import com.project.event_management_system.model.Person;


import java.util.Random;

public interface LoginService {

//    public Person getByEmail(String email);
//
//    public boolean checkByEmail(String email);
//
//    public boolean checkPassword(Person registration);

//    public Person loginAuthentication(String email, String password);

    public AuthenticationResponse authenticate(String email, String password);

}
