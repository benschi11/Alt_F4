/*
 *
 * Copyright (C) 2011 SW 11 Inc.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 * 
 * 
 */

/*
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
