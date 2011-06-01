
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
public class pdfToImageTest extends UnitTest
{
    
    @Test
            public void testPdfToImage()
    {
        Template test_template = new Template("test", "tex_test.tex", "ich", new Date(500), "mu", 10);        
     test_template.calculateForm();
        
     Helper.texToPdf(test_template.filename_, null);
     
     Helper.pdfToImage(null, null);
     
    }
    
}
