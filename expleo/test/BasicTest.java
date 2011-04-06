
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest
{

  @Test
  public void SimpleTemplateTest()
  {
    Template firstTemplate = new Template();
    firstTemplate.addCommand("user_");
    firstTemplate.addCommand("address_");

    firstTemplate.addSubstitution("user_", "Hannes");
    firstTemplate.addSubstitution("address_", "Graz");
    firstTemplate.addSubstitution("mobile_", "123456");

    Template emptyTemplate = firstTemplate.generateEmptyTemplate();
    emptyTemplate.addSubstitution("user_", "David");

    assertEquals(firstTemplate.getValue("user_"), "Hannes");
    assertEquals(firstTemplate.getValue("address_"), "Graz");
    assertEquals(firstTemplate.getValue("mobile_"), "123456");
    assertEquals(emptyTemplate.getValue("user_"), "David");
    assertEquals(emptyTemplate.getValue("address_"), "");
    assertEquals(emptyTemplate.getValue("mobile_"), "");
    
  }
}
