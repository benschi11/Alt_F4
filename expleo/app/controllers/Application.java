package controllers;

import java.io.File;
import play.mvc.*;

import java.util.*;

import models.*;
import models.generate.DocumentGenerator;
import play.Play;

public class Application extends Controller
{

    public static void index()
    {
        render();
    }

    public static void upload(String name, String description, File template)
    {
        if (template != null)
        {
            if (Template.upload(name, description, template))
            {
                flash.success("Template successfully uploaded.", null);
            }
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
        catch (Exception e)
        {
            render("Application/upload.html");
        }

    }

    public static void showSingleTemplate(long id)
    {
        Template template = Template.findById(id);

        render(template);
    }

    public static void simpleLink()
    {
        String applicationPath = Play.applicationPath.getAbsolutePath();
        File templateFile = new File(applicationPath + "/data/test/SimpleDocument.txt");
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("%name%", "John");

        DocumentGenerator generator = new DocumentGenerator(templateFile, replaceMap);
        models.generate.Document document = generator.create();
        String path = "/public/tmp/" + document.getFile().getName();
        render(path);
    }
}