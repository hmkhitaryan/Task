package com.sfl.login;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean validateUser(String user, String password) {
        return user.equalsIgnoreCase("in28Minutes") && password.equals("dummy");
    }

}