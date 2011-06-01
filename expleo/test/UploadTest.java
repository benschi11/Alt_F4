
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
        for (Template item : Template.<Template>findAll())
        {
            item.delete();
        }
        File testfile = new File("expleo/test/testfiles/temp1.txt");

        String error = Template.upload("testname", "test", testfile, "Benutzer", true);

        assertEquals(error, null);
        assertEquals(Template.count(), 1);

    }

    @Test
    public void uploadError()
    {
        for (Template item : Template.<Template>findAll())
        {
            item.delete();
        }
        
        File testfile = new File("expleo/test/testfiles/testfile_error.png");
        
        System.out.println(testfile.getAbsolutePath());

        String error = Template.upload("testname", "test", testfile, null, true);

        assertEquals(error, "File must be in Plaintext (UTF 8).");
        assertEquals(Template.count(), 0);
    }
}
