package com.project.event_management_system.service;

import com.project.event_management_system.model.Registration;

import java.util.Random;

public interface LoginService {

    public Registration getByEmail(String email);

    public boolean checkByEmail(String email);

    public boolean checkPassword(Registration registration);

    public Registration loginAuthentication(String email, String password);


    public static Integer generateOTP(){

            Random random = new Random();
            int num = random.nextInt(100000);
            String formatted = String.format("%05d", num);
            return Integer.parseInt(formatted);



    }
}
