
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest
{

  @Test
  public void SimpleTemplateTest()
  {
   /* Template firstTemplate = new Template("Temp1","/home/dave/Desktop/TemplateExpamples.txt");
    firstTemplate.addCommand("user_");
    firstTemplate.addCommand("address_");

    firstTemplate.addSubstitution("user_", "Hannes");
    firstTemplate.addSubstitution("address_", "Graz");
    firstTemplate.addSubstitution("mobile_", "123456");

    Template emptyTemplate = firstTemplate.generateEmptyTemplate("Temp2");
    emptyTemplate.addSubstitution("user_", "David");

    assertEquals(firstTemplate.getValue("user_"), "Hannes");
    assertEquals(firstTemplate.getValue("address_"), "Graz");
    assertEquals(firstTemplate.getValue("mobile_"), "123456");
    assertEquals(emptyTemplate.getValue("user_"), "David");
    assertEquals(emptyTemplate.getValue("address_"), "");
    assertEquals(emptyTemplate.getValue("mobile_"), "");  */
  }

  @Test
  public void SaveAndLoadTemplates()
  {
    /*Template firstTemplate = new Template("Temp1","/home/dave/Desktop/TemplateExpamples.txt");
    firstTemplate.addCommand("user_");
    firstTemplate.addCommand("address_");

    firstTemplate.addSubstitution("user_", "Hannes");
    firstTemplate.addSubstitution("address_", "Graz");
    firstTemplate.addSubstitution("mobile_", "123456");

    Template emptyTemplate = firstTemplate.generateEmptyTemplate("Temp2");
    emptyTemplate.addSubstitution("user_", "David");

    firstTemplate.save();
    emptyTemplate.save();

    Template loadedTemplate1 = Template.find("template_name_", "Temp1").first();
    Template loadedTemplate2 = Template.find("template_name_", "Temp2").first();

<<<<<<< HEAD
    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }
<<<<<<< HEAD

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
=======
    
    
>>>>>>> acab468ff2ab7853cbc7660e895cee8c3d2f6914
=======
    assertNotNull(loadedTemplate1);
    assertNotNull(loadedTemplate2);
>>>>>>> 4225b2ababb56b59189c454aa9f14cf7ee4c9578

    assertEquals(loadedTemplate1.getValue("user_"), "Hannes");
    assertEquals(loadedTemplate1.getValue("address_"), "Graz");
    assertEquals(loadedTemplate2.getValue("user_"), "David");
    assertEquals(loadedTemplate2.getValue("mobile_"), "");*/
  }
}
