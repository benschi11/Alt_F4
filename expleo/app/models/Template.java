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
    public String author_;
    public Date dateCreated_;
    public String description_;
    public int counterDownloads_;

    public Template(String name_, String filename_, String author_, Date dateCreated_, String description_, int counterDownloads_)
    {
        this.name_ = name_;
        this.filename_ = filename_;
        this.author_ = author_;
        this.dateCreated_ = dateCreated_;
        this.description_ = description_;
        this.counterDownloads_ = counterDownloads_;
    }
    
    

    public static Boolean upload(String name, String description, File template)
    {
        try
        {
            File copy_to = new File("public/templates/" + template.getName());

            //template.renameTo(copy_to);
            Copy.copy(template, copy_to);
            Date now = new Date();
            Template temp = new Template(name, template.getName(),"DummyAuthor", now, description, 4);
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
