
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
        String tags = "    Hallo   asd ,  wie  ,, geht ,,,  es   ? , , ,   ";
        List<String> tagList = null;
        tagList = createTags(tags);

        for (String item : tagList)
        {
            System.out.println("Tag: " + item + item.length());
        }

    }

    private List<String> createTags(String fullString)
    {
        fullString = fullString.replace("^\\s*", "");
        fullString = fullString.replace("\\s*$", "");
        fullString = fullString.replaceAll("\\s+", " ");
        String[] alltags = fullString.split(",");

        List<String> tagList = new ArrayList<String>();
        tagList.addAll(Arrays.asList(alltags));

        for (int index = 0; index < tagList.size(); index++)
        {
            tagList.set(index, tagList.get(index).replace("^\\s*", ""));
            tagList.set(index, tagList.get(index).replace("\\s*$", ""));
            if (tagList.get(index).length() == 0)
            {
                tagList.remove(index);
            }
            else if (tagList.get(index).equals(" "))
            {
                tagList.remove(index);
            }
        }
        return tagList;
    }
}
