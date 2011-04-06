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

  public String input_;

  public Template(String input)
  {
    input_ = input;
  }

}
