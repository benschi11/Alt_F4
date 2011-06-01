
import java.io.FileNotFoundException;
import java.util.Date;
import models.Helper;
import models.Template;
import org.junit.Test;
import play.test.UnitTest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author moped31
 */
public class TextToImageTest extends UnitTest
{
    
    
    @Test
    public void testConvertImage() throws FileNotFoundException
    {
        
     Template test_template = new Template("test", "label_test.txt", "ich", new Date(500), "mu", 10);        
     test_template.calculateForm();
        
     Helper.textToImage(test_template);
     
     
        
    }
    
}
