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
import play.Play;

/**
 *
 * @author Benedikt
=======
import java.util.*;
import java.util.regex.Pattern;
import javax.persistence.*;

import play.db.jpa.*;

/**
 *
 * @author dave
>>>>>>> t/form_generation
 */
@Entity
public class Template extends Model
{

    @Lob
  public String name_;
    @Lob
  public String filename_;
    @Lob
  public String author_;
  public Date dateCreated_;
    @Lob
  public String description_;
  
  public int counterDownloads_;
  @Lob
  public HashMap templates_ = new HashMap<String, String>();
  @Lob
  public String textFile;
  
  public String documentPath;

  public Template(String name_, String filename_, String author_, Date dateCreated_, String description_, int counterDownloads_)
  {
    this.name_ = name_;
    this.filename_ = filename_;
    this.author_ = author_;
    this.dateCreated_ = dateCreated_;
    this.description_ = description_;
    this.counterDownloads_ = counterDownloads_;


  }

  public void calculateForm()
  {
    this.textFile = new TextFile(Play.applicationPath.getAbsolutePath()+ "/public/templates/" + filename_).getText();

    Set<String> commands = new TreeSet<String>();

    String[] commands_temp = this.textFile.split("%%");

    for (int i = 1; i < commands_temp.length; i += 2)
    {
      commands.add(commands_temp[i]);
    }

    Iterator iterator = commands.iterator();

    while (iterator.hasNext())
    {
      String command = (String) iterator.next();
      templates_.put(command, "");
    }
    
    //this.textFile = null;

  }

  public static Boolean upload(String name, String description, File template)
  {
    try
    {
      File copy_to = new File("public/templates/" + template.getName());

      //template.renameTo(copy_to);
      Copy.copy(template, copy_to);
      Date now = new Date();
      Template temp = new Template(name, template.getName(), "DummyAuthor", now, description, 4);
      temp.save();

      return true;
    } catch (Exception e)
    {
      System.out.println(e.toString());
      return false;
    }
  }

  public static void delete(long id)
  {
    Template temp = Template.find("id", id).first();

    if (temp != null)
    {
      temp.delete();
    }
  }

  public void addCommand(String command)
  {
    templates_.put(command, "");
  }

  public void addSubstitution(String key, String userInput)
  {
    templates_.put(key, userInput);
  }

  public String getValue(String command)
  {
    return templates_.get(command).toString();
  }

  @Override
  public String toString()
  {
    return this.name_;
  }

  public HashMap getTemplates_()
  {
    return templates_;
  }

  public void doMap(Map<String, String[]> map)
  {
    Iterator mapIterator = map.keySet().iterator();
    while (mapIterator.hasNext())
    {
      String temp = (String) mapIterator.next();
      if (this.templates_.containsKey(temp))
      {
        this.addSubstitution(temp, map.get(temp)[0]);
      }
    }
  }
  
  
  
}



