/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class TagTest extends UnitTest
{

    @Test
    public void CreateAndRetrieveTags()
    {
        Date now = new Date();
        Template temp = new Template("tagtest", "tagtest.txt", "Hanni", now, "Whhoot?", 11);
        Template temp2 = new Template("tagtest2", "tagtest2.txt", "Hanni", now, "lala", 21);
        temp.tagItWith("Boom").save();
        temp2.tagItWith("Boom").save();
        temp.tagItWith("Hallo").save();

        List<Template> templates = Template.findTaggedWith("Boom");
        List<Template> templates2 = Template.findTaggedWith("Hallo");

        for (Template item : templates)
        {
            System.out.println("Templates tagged with Boom: " + item);
        }

        for (Template item : templates2)
        {
            System.out.println("Templates tagged with Hallo: " + item);
        }

        List<Tag> tags = Tag.findAll();

        for (Tag item : tags)
        {
            System.out.println("All Tags in Database: " + item);
        }

        assert (templates != null);
        assertEquals(templates2.size(), 1);

        temp.delete();
        temp2.delete();

    }
}
