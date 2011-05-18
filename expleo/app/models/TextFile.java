/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Lob;
import play.db.jpa.Model;
import utils.io.FileStringReader;

/**
 *
 * @author dave
 */
public class TextFile extends Model
{
  @Lob
    public String text;

  public TextFile(String datei)
  {
    try
    {
      //BufferedReader test = new BufferedReader(new FileReader(datei));
      FileStringReader reader = new FileStringReader(new File(datei));      
      text = reader.read();

    } catch (IOException ex)
    {
      System.out.println("Exception");
      Logger.getLogger(TextFile.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public String getText()
  {
    return text;
  }

  



}
