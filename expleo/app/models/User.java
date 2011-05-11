/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import play.*;
import play.db.jpa.*;

import java.util.*;
import javax.persistence.*;

/**
 *
 * @author slaven
 */

@Entity
public class User extends Model
{
    public String email_;
    public String password_;
    public String firstname_;
    public String lastname_;
    public String question_;
    public String answer_;
    public boolean admin_;


    public void register(String email, String password, String firstname, String lastname, String question, String answer)
    {
        this.email_ = email;
        this.password_ = password;
        this.firstname_ = firstname;
        this.lastname_ = lastname;
        this.question_ = question;
        this.answer_ = answer;
        this.admin_ = false;
    }




}
