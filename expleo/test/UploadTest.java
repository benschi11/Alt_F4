
import java.io.File;
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class UploadTest extends UnitTest
{

    @Test
    public void uploadSuccess()
    {
        Template.deleteAll();
        File testfile = new File("expleo/test/testfiles/temp1.txt");

        String error = Template.upload("testname", "test", testfile);

        assertEquals(error, null);
        assertEquals(Template.count(), 1);

    }

    @Test
    public void uploadError()
    {
        Template.deleteAll();
        
        File testfile = new File("expleo/test/testfiles/testfile_error.png");
        
        System.out.println(testfile.getAbsolutePath());

        String error = Template.upload("testname", "test", testfile);

        assertEquals(error, "File must be in Plaintext (UTF 8).");
        assertEquals(Template.count(), 0);
    }
}
