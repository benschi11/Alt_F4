
import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;
import java.util.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author colors_united
 */
public class TagSplittingTest extends FunctionalTest
{

    @Test
    public void TagSplitting()
    {
        String tags = "    Hallo   Hanni ,  wie  ,, geht ,,es          "
                + "  ,,,,,,  , ,    ,   ,  dir   ? , , ,   ";
        List<String> tagList = null;
        tagList = createTags(tags);

        for (String item : tagList)
        {
            System.out.println("Tag: " + item + item.length());
        }

        assertEquals("Hallo Hanni", tagList.get(0));
        assertEquals("wie", tagList.get(1));
        assertEquals("geht", tagList.get(2));
        assertEquals("es", tagList.get(3));
        assertEquals("dir ?", tagList.get(4));

    }

    @Test
    public void CreateUniqueTags()
    {
        for (Template item : Template.<Template>findAll())
        {
            item.delete();
        }

        Date now = new Date();
        Template temp = new Template("tagtest", "tagtest.txt", "Hanni", now, "Whhoot?", 11);

        String tags = "    Hallo   Hanni ,  wie  ,, geht ,,es          "
                + "  ,,,,,,  , ,    ,   ,  dir   ? , , ,  ,Hallo, Hallo  ,  H allo,   ";
        List<String> tagList = null;
        tagList = createTags(tags);

        temp.tagItWith(tagList).save();

        List<Tag> taggings = Tag.findAll();
        for (Tag item : taggings)
        {
            System.out.println("Tags in Database: " + item);
        }
        List<Tag> hallo = Tag.find("name", "Hallo").fetch(5);
        assertEquals(taggings.size(), 9);
        assertEquals(hallo.size(), 1);
    }

    private List<String> createTags(String fullString)
    {
        //fullString = fullString.replace("^\\s*", "");
        //fullString = fullString.replace("\\s*$", "");
        fullString = fullString.replaceAll("\\s+", " ");
        String[] alltags = fullString.split(",");

        List<String> tagList = new ArrayList<String>();
        List<String> realTags = new ArrayList<String>();
        tagList.addAll(Arrays.asList(alltags));

        for (int index = 0; index < tagList.size(); index++)
        {
            //tagList.set(index, tagList.get(index).replace("^\\s*", ""));
            //tagList.set(index, tagList.get(index).replace("\\s*$", ""));
            tagList.set(index, tagList.get(index).trim());
            if ((tagList.get(index).length() > 0) && (tagList.get(index).equals(" ")) == false)
            {
                realTags.add(tagList.get(index));
            }
        }
        return realTags;
    }
}
