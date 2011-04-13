
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import play.Play;
import play.test.UnitTest;
import utils.io.FileStringReader;
import utils.io.FileStringWriter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Entwickler
 */
public class SimpleIoTest extends UnitTest {

    private final String applicationPath = Play.applicationPath.getAbsolutePath();

    @Test
    public void testSimpleRead() {
        File file = new File(applicationPath + "/data/test/SimpleRead.txt");
        FileStringReader reader = new FileStringReader(file);
        String readString = null;
        try {
            readString = reader.read();
        } catch (FileNotFoundException fileNotFoundException) {
            assertTrue("File '" + file.getAbsolutePath() + "' not found.", false);
        } catch (IOException iOException) {
            assertTrue(iOException.getMessage(), false);
        }
        String expectedString = "Hello Word!";
        assertEquals("Read String (" + readString + ") differs from expected String ("
                + expectedString + ")", readString, expectedString);
    }

    @Test
    public void testSimpleWrite() {
        File file = new File(applicationPath + "/data/test/SimpleWrite.txt");
        FileStringWriter writer = new FileStringWriter(file);
        String writeString = Long.toString(System.currentTimeMillis());
        try {
            writer.write(writeString);
        } catch (IOException iOException) {
            assertTrue(iOException.getMessage(), false);
        }

        FileStringReader reader = new FileStringReader(file);
        String readString = null;
        try {
            readString = reader.read();
        } catch (FileNotFoundException fileNotFoundException) {
            assertTrue("File '" + file.getAbsolutePath() + "' not found.", false);
        } catch (IOException iOException) {
            assertTrue(iOException.getMessage(), false);
        }

        assertEquals("Read String (" + readString + ") differs from expected String ("
                + writeString + ")", readString, writeString);
    }
}
