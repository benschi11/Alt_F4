
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import play.test.UnitTest;
import utils.Substitution;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Entwickler
 */
public class SubstitutionTest extends UnitTest {

    @Test
    public void testSingleSubstitution() {
        String text = "My name is %%name%%. I live in %%country%%.";
        Map<String, String> replacingMap = new HashMap<String, String>();
        replacingMap.put("name", "Forrest, Forrest Gump");
        replacingMap.put("country", "Alabama");

        Substitution substitution = new Substitution(text);
        substitution.replace(replacingMap);

        assertEquals("Failed to replace String",
                "My name is Forrest, Forrest Gump. I live in Alabama.",
                substitution.getText());
    }

    @Test
    public void testMultipleSubstitution() {
        String text = "My name is %%name%%. I live in %%country%%.";
        Map<String, String> replacingMap1 = new HashMap<String, String>();
        replacingMap1.put("name", "Forrest, Forrest Gump");
        Map<String, String> replacingMap2 = new HashMap<String, String>();
        replacingMap2.put("country", "Alabama");

        Substitution substitution = new Substitution(text);
        substitution.replace(replacingMap1);
        substitution.replace(replacingMap2);

        assertEquals("Failed to replace String",
                "My name is Forrest, Forrest Gump. I live in Alabama.", substitution.getText());
    }
}
