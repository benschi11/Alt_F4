import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void TemplateModelTest()
    {
        Template temp = new Template("test", "testfile.txt");

        assertEquals(temp.name_, "test");
        assertEquals(temp.filename_, "testfile.txt");
    }

    @Test
    public void TemplateStorageTest()
    {
        Template.deleteAll();

        Template temp1 = new Template("temp1","temp1.txt");
        temp1.save();

        Template temp2 = new Template("temp2","temp2.txt");
        temp2.save();

        assertEquals(2, Template.findAll().size());
    }

    @Test
    public void TemplateDeleteTest()
    {
        Template.deleteAll();

        Template temp = new Template("temp", "temp1.txt");
        temp.save();

        Template temp2 = new Template("temp2", "temp2.txt");
        temp2.save();

        Template.delete(temp.getId());

        assertEquals(1, Template.findAll().size());

        Template.delete(temp2.getId());

        assertEquals(0, Template.findAll().size());
    }

}
