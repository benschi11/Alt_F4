/*
 * COPYRIGHT INFORMATION
 * 
 * Developed by ALTernative + F4ntastic FOUR
 * 
 *  This program is free software; you can redistribute it and/or modify
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
 */

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
import utils.io.FileStringReader;
import play.Play;

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
    @ManyToMany
    public Set<Tag> tags;
    @Lob
    public String textFile;
    public String documentPath;
    public String pathToFilledFile;
    public String userRegistered;
    public Boolean isHidden;

    public Template(String name_, String filename_, String author_, Date dateCreated_, String description_, int counterDownloads_)
    {
        this.name_ = name_;
        this.filename_ = filename_;
        this.author_ = author_;
        this.dateCreated_ = dateCreated_;
        this.description_ = description_;
        this.counterDownloads_ = counterDownloads_;
        this.tags = new TreeSet<Tag>();
        this.pathToFilledFile = null;
        this.userRegistered = null;
        this.isHidden = false;


    }

    public void calculateForm()
    {
        this.textFile = new TextFile(Play.applicationPath.getAbsolutePath() + "/public/templates/" + filename_).getText();

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

    }

    public static String upload(String name, String description, File template, String userRegistered, Boolean isHidden)
    {
        try
        {
            FileStringReader reader = new FileStringReader(template);
            String text = reader.read();


            if (!Helper.isUtf8(text))
            {
                return "File must be in Plaintext (UTF 8).";
            }

            String author = userRegistered;


            Date now = new Date();
            Template temp = new Template(name, template.getName(), author, now, description, 4);
            temp.userRegistered = userRegistered;
            temp.isHidden = isHidden;
            temp.save();

            int dotPos = template.getName().lastIndexOf(".");
            String newName;
            String extension;

            if (dotPos != -1)
            {
                extension = template.getName().substring(dotPos);
                newName = temp.id + "_" + name + extension;
            }
            else
            {
                newName = temp.id + "_" + name;
            }


            File copy_to = new File("expleo/public/templates/" + newName);

            System.out.println(copy_to.getAbsolutePath());
            Helper.copy(template, copy_to);

            temp.filename_ = newName;
            temp.calculateForm();
            temp.save();

            return null;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return e.toString();
        }
    }

    //this.textFile = null;
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

    public Template tagItWith(String name)
    {
        tags.add(Tag.findOrCreateByName(name));
        return this;
    }

    public static List<Template> findTaggedWith(String tag)
    {
        return Template.find("select distinct tp from Template tp join tp.tags as t where t.name = ?", tag).fetch();
    }
}
