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

public class Application extends Controller {


    public static void index() {
        render();
    }

    public static void upload(String name, String description, File template)
    {
        String upload = request.params.get("upload");
        Boolean success = false;

        if (upload != null)
        {
            validation.clear();
            validation.required(name).message("Please insert a name.");
            validation.required(template).message("Please select a file.");
        }

        if (template != null)
        {
            String error = Template.upload(name, description, template);
            if (error == null)
            {
                success = true;
                
            }
            else
            {
                 Errors.displayInlineError(1,"Template has to be a plain-text file (encoded in UTF-8).", "../Application/upload");
            }
        }

        render(name, description, template, success);

    }

    public static void showAllTemplates() {

        // Template Test

        /*Template.deleteAll();       
        Template template = new Template("example", "example.txt", "niemand2", new Date(123456l), "Example config", 15000);
        template.calculateForm();      
        
        template.save();*/

        try {

            List<Template> all_templates = Template.findAll();
            
            render(all_templates);
        } catch (Exception e) {
           System.out.println(e.getMessage());
            render("Application/upload.html");
        }

    }

    public static void showSingleTemplate(long id) {
        Template template = Template.findById(id);

        render(template);
    }

    
    public static void showSingleTemplate(Template template)
    {
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("TEMPLATE: " + template);
        render(template);
    }


    public static void simpleLink() {
        String applicationPath = Play.applicationPath.getAbsolutePath();
        File templateFile = new File(applicationPath + "/data/test/SimpleDocument.txt");
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("%name%", "John");


        DocumentGenerator generator = new DocumentGenerator(templateFile, replaceMap);
        models.generate.Document document = generator.create();
        String path = "/public/tmp/" + document.getFile().getName();
        render(path);
    }

    public static void selectedTemplate(Long id) {
        Template loadedTemplate = Template.findById(id);
        render(loadedTemplate);
    }


    public static void register() {
        render();
    }

    public static void doRegister(String email, String password, String passwordagain,
            String firstname, String lastname, String question, String answer) {

        List<String> Errors = new ArrayList();
        boolean errorStatus = false;




        if ((firstname.length() == 0) || (lastname.length() == 0)) {
            Errors.add("Please type your name");
            errorStatus = true;
        }


        if (email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == false) {
            Errors.add("Type in the e-mail correctly");
            errorStatus = true;
        }

        if (password.length() < 6) {
            Errors.add("Password too short. At least 6 characters");
            errorStatus = true;

        }
        else if(password.equals(passwordagain) == false)
        {
            Errors.add("Passwords don't match");
            errorStatus = true;
        }


        if (question.length() != 0 || answer.length() != 0) {
            if (answer.length() == 0 || question.length() == 0) {
                Errors.add("Please type in your answer and question");
                errorStatus = true;
            }
        }

        User checkUser = User.find("email_", email).first();
        if (checkUser != null) {
            Errors.add("User already exists, try another email address");
            errorStatus = true;
        }

        if (errorStatus == false) {
            User newuser = new User();
            newuser.register(email, password, firstname, lastname, question, answer);
            newuser.save();
            Errors.add("User " + email + " successfull registered");
        }
        render(Errors, errorStatus);

        
    }


    public static void insertionComplete() {
        //Template template = Template.findById(id);

        Map<String, String[]> map = request.params.all();
        Template template = Template.findById(Long.decode(map.get("ID")[0]));

        template.doMap(map);

        Iterator iterator = template.templates_.keySet().iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            System.out.println("Key: " + key + " value: " + template.templates_.get(key));
        }

        DocumentGenerator generator = new DocumentGenerator(new File(Play.applicationPath.getAbsolutePath()+"/public/templates/" + template.filename_), template.getTemplates_());

        Document document = generator.create();

        System.out.println(document);
        System.out.println(document.getFile());
        System.out.println(document.getFile().getAbsolutePath());
        System.out.println(document.getContent());

//      document.getFile().delete();

        template.pathToFilledFile = document.getFile().getAbsolutePath();

        render(template);

        

    }
}
