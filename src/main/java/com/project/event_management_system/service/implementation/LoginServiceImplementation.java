package com.project.event_management_system.service.implementation;

import com.project.event_management_system.model.Registration;
import com.project.event_management_system.repository.LoginRepository;
import com.project.event_management_system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplementation implements LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginServiceImplementation(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    @Override
    public Registration getByEmail(String email) {
        return this.loginRepository.findByEmail(email);
    }

    @Override
    public boolean checkByEmail(String email) {
        Registration user = this.loginRepository.findByEmail(email);

        if(user != null) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean checkPassword(Registration registration) {

        boolean flag = checkByEmail(registration.getEmail());


        if(flag) {
            return getByEmail(registration.getEmail()).getPassword().equals(registration.getPassword());
        }

        else {
            return false;
        }
    }

    @Override
    public Registration loginAuthentication(String email, String password) {
        return this.loginRepository.getDataForAuthentication(email, password);
    }
}
