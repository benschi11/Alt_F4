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
        for (Template item : Template.<Template>findAll())
        {
            item.delete();
        }

        Date now = new Date();
        Template temp = new Template("tagtest", "tagtest.txt", "Hanni", now, "Whhoot?", 11);
        Template temp2 = new Template("tagtest2", "tagtest2.txt", "Hanni", now, "lala", 21);
        Template temp3 = new Template("tagtest3", "tagtest3.txt", "Hanni", now, "123", 1);

        List<String> tagList1 = new ArrayList();
        List<String> tagList2 = new ArrayList();
        List<String> tagList3 = new ArrayList();
        tagList1.add("Boom");
        tagList1.add("Hallo");
        tagList2.add("Hallo123");
        tagList3.add("Hallo");


        temp.tagItWith(tagList1).save();
        temp2.tagItWith(tagList1).save();
        temp.tagItWith(tagList2).save();
        temp3.tagItWith(tagList3).save();
        temp.tagItWith(Arrays.asList("Hallo"));

        List<Template> templates = Template.findTaggedWith("Boom");
        List<Template> templates2 = Template.findTaggedWith("Hallo");
        List<Template> templates3 = Template.findTaggedWith("Hallo123");

        for (Template item : templates)
        {
            System.out.println("Templates tagged with Boom: " + item);
        }

        for (Template item : templates2)
        {
            System.out.println("Templates tagged with Hallo: " + item);
        }

        for (Template item : templates3)
        {
            System.out.println("Templates tagged with Hallo123: " + item);
        }

        List<Tag> tags = Tag.findAll();

        for (Tag item : tags)
        {
            System.out.println("All Tags in Database: " + item);
        }


        assert (templates != null);
        assertEquals(templates2.size(), 3);
        assertEquals(templates.size(), 2);
        assertEquals(templates3.size(), 1);

    }
}
