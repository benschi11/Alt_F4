import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class AdminTest extends UnitTest {

    @Test
    public void testFirstUserIsAdmin() {
        User.deleteAll();
        
        User admin = new User("admin@test.at", "password", "Max", "Mustermann", "", "");
        admin.save();
        
        assertEquals(true, admin.admin_);
        
        User user = new User("user@user.at","password","Hans", "Gruber","","");
        user.save();
        assertEquals(false, user.admin_);
        
        User.deleteAll();
        
    }
    
}