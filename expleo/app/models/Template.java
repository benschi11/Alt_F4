/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.*;
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

  public Template(String template_name_)
  {
    this.template_name_ = template_name_;
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
    Template newTemplate = new Template(template_name);
    Iterator iterator = templates_.keySet().iterator();
    while(iterator.hasNext())
    {
     newTemplate.addCommand(iterator.next().toString());
    }

    return newTemplate;
  }
  
}
