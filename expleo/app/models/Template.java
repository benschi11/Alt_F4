/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.File;
import play.db.jpa.*;
import play.data.validation.*;

import java.util.*;
import javax.persistence.*;

/**
 *
 * @author Benedikt
 */
@Entity
public class Template extends Model
{

    public String name_;
    public String filename_;

    public Template(String name, String filename)
    {
        this.name_ = name;
        this.filename_ = filename;
    }

    public static Boolean upload(String name, File template)
    {
        try
        {
            File copy_to = new File("public/templates/" + template.getName());

            //template.renameTo(copy_to);
            Copy.copy(template, copy_to);
            Template temp = new Template(name, template.getName());
            temp.save();
            
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public static void delete(long id)
    {
        Template temp = Template.find("id", id).first();
        
        if(temp != null)
        {
            temp.delete();
        }
    }
}
