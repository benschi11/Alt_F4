package controllers;

import java.io.File;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.generate.Document;

import models.generate.DocumentGenerator;
import play.Play;
import play.data.validation.Required;

public class Application extends Controller
{

    public static void index()
    {
        render();
    }

    @Before
    static void setConnectedUser()
    {
        if (Security.isConnected())
        {
            User user = User.find("email_", Security.connected()).first();
            renderArgs.put("user", user.email_);
        }
    }

    public static void upload(String name, String description, File template)
    {
        String upload = request.params.get("upload");
        Boolean success = false;
        
        String user = Security.connected();
        
 
            
            
        

        if (upload != null)
        {
            validation.clear();
            validation.required(name).message("Please insert a name.");
            validation.required(template).message("Please select a file.");
        }

        if (template != null) {
            String error = Template.upload(name, description, template, user);
            if (error == null) {
                success = true;

            }
            else
            {
                Errors.displayInlineError(1, "Template has to be a plain-text file (encoded in UTF-8).", "../Application/upload");
            }
        }

        render(name, description, template, success);

    }

    public static void showAllTemplates()
    {

        // Template Test

        /*Template.deleteAll();       
        Template template = new Template("example", "example.txt", "niemand2", new Date(123456l), "Example config", 15000);
        template.calculateForm();      
        
        template.save();*/

        try
        {

            List<Template> all_templates = Template.findAll();

            render(all_templates);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
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

    public static void selectedTemplate(Long id)
    {
        Template loadedTemplate = Template.findById(id);
        render(loadedTemplate);
    }

    public static void register()
    {
        render();
    }

    public static void insertionComplete()
    {
        //Template template = Template.findById(id);

        Map<String, String[]> map = request.params.all();
        Template template = Template.findById(Long.decode(map.get("ID")[0]));

        template.doMap(map);

        Iterator iterator = template.templates_.keySet().iterator();

        while (iterator.hasNext())
        {
            String key = (String) iterator.next();
            System.out.println("Key: " + key + " value: " + template.templates_.get(key));
        }

        DocumentGenerator generator = new DocumentGenerator(new File(Play.applicationPath.getAbsolutePath() + "/public/templates/" + template.filename_), template.getTemplates_());

        Document document = generator.create();

        System.out.println(document);
        System.out.println(document.getFile());
        System.out.println(document.getFile().getAbsolutePath());
        System.out.println(document.getContent());

//      document.getFile().delete();

        template.pathToFilledFile = document.getFile().getAbsolutePath().replaceAll(Play.applicationPath.getAbsolutePath(), "");

        render(template);



    }
}
