/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.*;
import java.util.regex.Pattern;
import javax.persistence.*;

import play.db.jpa.*;

/**
 *
 * @author dave
 */
@Entity
public class Template extends Model
{
  public String template_name_;
  public HashMap templates_ = new HashMap<String, String>();
  public TextFile textFile;

  public Template(String template_name_,String file)
  {
    this.template_name_ = template_name_;
    this.textFile = new TextFile(file);

    Set<String> commands = new TreeSet<String>();

    String[] commands_temp = this.textFile.getText().split("%%");

    for(int i=1; i < commands_temp.length; i+=2)
    {
      commands.add(commands_temp[i]);
    }

    Iterator iterator = commands.iterator();

    while(iterator.hasNext())
    {
      String command = (String) iterator.next();
      System.out.println("Kommando: "+command);
      templates_.put(command,"");
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

  public Template generateEmptyTemplate(String template_name)
  {
    /*Template newTemplate = new Template(template_name,);
    Iterator iterator = templates_.keySet().iterator();
    while(iterator.hasNext())
    {
     newTemplate.addCommand(iterator.next().toString());

    }

    return newTemplate;*/
    return null;
  }

  @Override
  public String toString()
  {
    return this.template_name_;
  }

  public HashMap getTemplates_()
  {
    return templates_;
  }

  public void doMap(Map<String, String[]> map)
  {
    Iterator mapIterator = map.keySet().iterator();
    while(mapIterator.hasNext())
    {
      String temp = (String) mapIterator.next();
      if(this.templates_.containsKey(temp))
        this.addSubstitution(temp, map.get(temp)[0]);
    }
  }
  
  
}
