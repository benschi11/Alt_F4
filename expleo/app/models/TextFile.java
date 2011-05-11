/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Lob;
import play.db.jpa.Model;

/**
 *
 * @author dave
 */
public class TextFile extends Model
{
  @Lob
    private String text;

  public TextFile(String datei)
  {
    try
    {
      BufferedReader test = new BufferedReader(new FileReader(datei));
      String input= "";
      text = "";
      
      while((input = test.readLine()) != null)
      {
        this.text += input;
        this.text += "\n";
      }

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
