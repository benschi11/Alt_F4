/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Entwickler
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import play.Play;
import play.test.UnitTest;
import utils.io.FileStringReader;
import utils.io.FileStringWriter;
import models.*;
import controllers.Security;



public class tryUserConnect extends UnitTest {

    @Test
public void tryConnectAsUser() {
   User.deleteAll();
    // Create a new user and save it
   //snew User("test@gmail.com", "secret", "User", "Last", "test", "tests").save();

	User u = new User("test@gmail.com", "secret", "test", "test" , "test", "test");
	u.save();

   assertEquals(User.count(), 1);
    // Test
   assertNotNull(User.connect("test@gmail.com", "secret"));
   assertNull(User.connect("test@gmail.com", "badsecret"));
   //assertNull(User.connect("test@gmail.com", "secret"));
}



}