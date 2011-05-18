/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import play.mvc.*;

import java.util.*;

import models.*;

/**
 *
 * @author slaven
 */
public class Registration extends Controller {

    public static void doRegister(String email, String password, String passwordagain,
            String firstname, String lastname, String question, String answer) {

        List<String> Errors = new ArrayList();
        boolean errorStatus = false;




        if ((firstname.length() == 0) || (lastname.length() == 0)) {
            Errors.add("Please type your name");
            errorStatus = true;
        }


        if (email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == false) {
            Errors.add("Type in the e-mail correctly");
            errorStatus = true;
        }

        if (password.length() < 6) {
            Errors.add("Password too short. At least 6 characters");
            errorStatus = true;

        } else if (password.equals(passwordagain) == false) {
            Errors.add("Passwords don't match");
            errorStatus = true;
        }


        if (question.length() != 0 || answer.length() != 0) {
            if (answer.length() == 0 || question.length() == 0) {
                Errors.add("Please type in your answer and question");
                errorStatus = true;
            }
        }

        User checkUser = User.find("email_", email).first();
        if (checkUser != null) {
            Errors.add("User already exists, try another email address");
            errorStatus = true;
        }

        if (errorStatus == false) {
            User newuser = new User();
            newuser.register(email, password, firstname, lastname, question, answer);
            newuser.save();
            Errors.add("User " + email + " successfull registered");
        }
        render(Errors, errorStatus);


    }
}
