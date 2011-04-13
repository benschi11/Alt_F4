import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class TemplateTest extends UnitTest {

    @Test
    public void TemplateModelTest()
    {
        Date now = new Date();
        Template temp = new Template("test", "testfile.txt", "author", now,"Description", 4);
        
        assertEquals(temp.name_, "test");
        assertEquals(temp.filename_, "testfile.txt");
        assertEquals(temp.author_, "author");
        assertEquals(temp.dateCreated_, now);
        assertEquals(temp.counterDownloads_, 4);
    }
    
    @Test
    public void TemplateStorageTest()
    {
        Template.deleteAll();
        
        Date now = new Date();
        Template temp1 = new Template("test1", "testfile.txt", "author", now, "Description", 4);
        
        temp1.save();
        
        Template temp2 = new Template("test2", "testfile.txt", "author", now, "Description", 4);
        temp2.save();
        
        assertEquals(2, Template.findAll().size());
    }
    
    @Test
    public void TemplateDeleteTest()
    {
        Template.deleteAll();
        
        Date now = new Date();
        Template temp1 = new Template("test1", "testfile.txt", "author", now, "Description", 4);
        temp1.save();
        
        Template temp2 = new Template("test2", "testfile.txt", "author", now, "Description", 4);
        temp2.save();
        
        Template.delete(temp1.getId());
        
        assertEquals(1, Template.findAll().size());
        
        Template.delete(temp2.getId());
        
        assertEquals(0, Template.findAll().size());
    }
}
