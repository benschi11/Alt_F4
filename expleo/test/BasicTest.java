
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

    assertNotNull(loadedTemplate1);
    assertNotNull(loadedTemplate2);

    assertEquals(loadedTemplate1.getValue("user_"), "Hannes");
    assertEquals(loadedTemplate1.getValue("address_"), "Graz");
    assertEquals(loadedTemplate2.getValue("user_"), "David");
    assertEquals(loadedTemplate2.getValue("mobile_"), "");*/
  }
}
