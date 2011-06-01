
import java.io.File;
import java.util.Date;
import models.Helper;
import models.Template;
import org.junit.Test;
import play.Play;
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
     
        System.out.println("filename: "+test_template.filename_);
     
     File fili = new File(Play.applicationPath.getAbsolutePath()+"/public/templates/"+test_template.filename_);
        
     Helper.texToPdf(fili,new File(fili.getParent()));
     
     //Helper.pdfToImage(null, null);
     
    }
    
}
