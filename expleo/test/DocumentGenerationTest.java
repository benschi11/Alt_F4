
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import models.generate.Document;
import models.generate.DocumentGenerator;
import org.junit.Test;
import play.Play;
import play.test.UnitTest;
import utils.io.FileStringReader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Entwickler
 */
public class DocumentGenerationTest extends UnitTest {

    private final String applicationPath = Play.applicationPath.getAbsolutePath();

    @Test
    public void testSimpleGeneration() {
        File templateFile = new File(applicationPath + "/data/test/1_SimpleDocument.txt");
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("name", "John");

        DocumentGenerator generator = new DocumentGenerator(templateFile, replaceMap);
        File document = generator.create();

        assertTrue("Document creation failed.", document != null);
        try
        {
        assertEquals("Substition failed", new FileStringReader(document).read(), "My name is John!");
        }catch(Exception ex)
        {
            assertTrue(false);
            
        }

        document.delete();
    }
}
