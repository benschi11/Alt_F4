/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Tag extends Model implements Compareable<Tag>
{

    public String name;

    private Tag(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return name;
    }

    public int compareTo(Tag otherTag)
    {
        return name.compareTo(otherTag.name);
    }

    public static Tag findOrCreateByName(String name)
    {
        Tag tag = Tag.find("byName", name).first();
        if (tag == null)
        {
            tag = new Tag(name);
        }
        return tag;
    }
}
