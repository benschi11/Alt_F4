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

    public static void upload(String name, String description, File template)
    {
        if (Template.upload(name, description, template))
        {
            flash.success("Template successfully uploaded.", null);
        }
        render();
    }

    public static void showAllTemplates()
    {
        try
        {
            List<Template> all_templates = Template.findAll();
            render(all_templates);
        }
        catch(Exception e)
        {
            render("Application/upload.html");
        }

    }

    public static void showSingleTemplate(long id)
    {
        Template template = Template.findById(id);

        render(template);
    }
}