
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class RegisterTest extends UnitTest {
    

    @Test
    public void ValidRegistration() {

        User.deleteAll();

        String firstname, lastname, email, password, passwordagain, question, answer;
        firstname = "Hans";
        lastname = "imGlück";
        email = "hans@student.at";
        password = "hans123";
        passwordagain = "hans123";
        question = "Frage";
        answer ="DummeAntwort";

        
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

        assert(errorStatus == false);


        User loadUser = User.find("email_", email).first();

        String nameOfUser = loadUser.firstname_;

        assertEquals(nameOfUser, firstname);
        assertEquals(loadUser.password_, passwordagain);

    }

     @Test
    public void FailedRegistration() {

        User.deleteAll();

        String firstname, lastname, email, password, passwordagain, question, answer;
        firstname = "Hans";
        lastname = "";
        email = "hans.student.graz";
        password = "hans123";
        passwordagain = "h3";
        question = "Frage";
        answer ="";


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

        assert(errorStatus == false);
        assertNotSame(password, passwordagain);

        //Uncomment to see ErrorMessages in Terminal
       // for(String item : Errors)
        //{
          //  System.out.println("Error:" + item);
        //}

        

    }
}
