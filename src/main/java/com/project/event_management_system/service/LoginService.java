package com.project.event_management_system.service;

import com.project.event_management_system.model.AuthenticationResponse;

public interface LoginService {

    public AuthenticationResponse authenticate(String email, String password);

}
