package controllers;

import java.io.File;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller
{

    public static void index()
    {
        render();
    }

    public static void upload(String name, File template)
    {
        if(Template.upload(name, template))
        {
            flash.success("Template successfully uploaded.", null);
        }
        render();
    }
    
    public static void showAllTemplates()
    {
        List<Template> all_templates = Template.findAll();
        
        render(all_templates);
    }
}