
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;
import models.Template;
import play.test.UnitTest;
import org.junit.Test;
import play.Play;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author moped31
 */
public class LabelTest extends UnitTest
{
    @Test
    public void testLabelSubstition()
    {
        Template test_template = new Template("test", "label_test.txt", "ich", new Date(500), "mu", 10);
        
        test_template.calculateForm();
        
        
        Iterator label1 = test_template.labels_.iterator("label1");
        Iterator label2 = test_template.labels_.iterator("label2");
        Iterator no_label = test_template.labels_.iterator("0");
        
        assertEquals("key1", label1.next());
        assertEquals("key2", label1.next());
        try
        {
            // testen ob ein zweiter datensatz mit gleichem key und label vorkommt
            label1.next();
            // falls vorkommt fehlschlagen
            assertEquals(true, false);
        }catch(NoSuchElementException ex)
        {
            assertEquals(true,true);
        }

        assertEquals("key1", label2.next());
        assertEquals("key2", label2.next());
        assertEquals("key1", no_label.next());
        assertEquals("key2", no_label.next());
        
        
    }
    
    
    
    @Test
            public void testReihenfolge()
    {
       Template test_template = new Template("test", "label_test.txt", "ich", new Date(500), "mu", 10);
        
        test_template.calculateForm();
        Vector v = new Vector(test_template.labels_.keySet());
        Collections.sort(v);
        // label 0 (ohne labels) sollte als erstes sein
        assertEquals("0",v.iterator().next());       
    }
    
}
