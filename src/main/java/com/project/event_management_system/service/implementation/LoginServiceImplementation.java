package com.project.event_management_system.service.implementation;

import com.project.event_management_system.enums.EmailVerificationStatus;
import com.project.event_management_system.model.AuthenticationResponse;
import com.project.event_management_system.model.User;
import com.project.event_management_system.repository.LoginRepository;
import com.project.event_management_system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImplementation implements LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginServiceImplementation(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

//
//    @Override
//    public Person getByEmail(String email) {
//        return this.loginRepository.findByEmail(email)
//                .orElseThrow(()->new RuntimeException("Person with email "+email+ " not found" ));
//    }
//
//    @Override
//    public boolean checkByEmail(String email) {
//        Optional<Person> person = this.loginRepository.findByEmail(email);
//
//        return person.isPresent();
//    }
//
//    @Override
//    public boolean checkPassword(Person registration) {
//
//        boolean flag = checkByEmail(registration.getEmail());
//
//        if(flag) {
//            return getByEmail(registration.getEmail()).getPassword().equals(registration.getPassword());
//        }
//
//        else {
//            return false;
//        }
//    }
//
////    @Override
////    public Person loginAuthentication(String email, String password) {
////        return this.loginRepository.getDataForAuthentication(email, password);
////    }

    @Override
    public AuthenticationResponse authenticate(String email, String password) {
        //check if person with email exists
        Optional<User> person  = this.loginRepository.findByEmail(email);
        if (person.isEmpty()){
            throw new RuntimeException("Email does not exist");
        }

        //check password
        if (!(person.get().getPassword().equals(password))){
            throw new RuntimeException("Incorrect Password");
        }

        //check if email address is validated
        if (person.get().getEmailVerificationStatus() == EmailVerificationStatus.PENDING){
            throw new RuntimeException("Please validate your email id");
        }

        return AuthenticationResponse.builder()
                .firstName(person.get().getFirstName())
                .lastName(person.get().getLastName())
                .email(person.get().getEmail())
                .contactNumber(person.get().getContactNumber())
                .build();

    }
}
