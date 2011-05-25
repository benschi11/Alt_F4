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

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            User user = User.find("email_", Security.connected()).first();
            if (user != null) {
                renderArgs.put("user", user);
            }
        }
    }

    public static void upload(String name, String description, File template, Boolean isHidden) {
        String upload = request.params.get("upload");
        Boolean success = false;

        String user = Security.connected();

        System.out.println("ISHIDDEN: " + request.params.get("isHidden"));

        String hidden = request.params.get("isHidden");

        if (hidden == null) {
            isHidden = false;
        } else {
            isHidden = true;
        }


        if (upload != null) {
            validation.clear();
            validation.required(name).message("Please insert a name.");
            validation.required(template).message("Please select a file.");
        }

        if (template != null) {
            String error = Template.upload(name, description, template, user, isHidden);
            if (error == null) {
                success = true;

            } else {
                Errors.displayInlineError(1, "Template has to be a plain-text file (encoded in UTF-8).", "../Application/upload");
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

    public static void UserCP() {
        render("Application/usercp.html");
    }

    public static void editProfile() {
        User usercur = User.find("email_", Security.connected()).first();
        render("Application/editProfile.html", usercur);
    }

    public static void doEditProfile(String username, String password, String passwordagain,
            String firstname, String lastname, String question, String answer) {

        List<String> Errors = new ArrayList();
        boolean errorStatus = false;
        User user = User.find("email_", username).first();
        String email = user.email_;



        if ((firstname.length() == 0) || (lastname.length() == 0)) {
            Errors.add("Please type your name");
            errorStatus = true;
        }

        if (password.length() < 6 && password.length() > 0) {
            Errors.add("Password too short. At least 6 characters");
            errorStatus = true;

        } else if (password.equals(passwordagain) == false) {
            Errors.add("Passwords don't match");
            errorStatus = true;
        }


        if (question.length() != 0 || answer.length() != 0) {
            if (answer.length() == 0 || question.length() == 0) {
                Errors.add("Please type in your answer and question");
                errorStatus = true;
            }
        }


        if (errorStatus == false) {


            user.edit(password, firstname, lastname, question, answer);
            user.save();
            Errors.add("User " + email + " successfull edited");
        }
        render(Errors, errorStatus);
    }

    public static void editTemplates() {

        List<Template> all_templates = Template.findAll();

        render(all_templates);

    }

    public static void editOtherProfile() {
        List<Template> all_users = User.findAll();

        render(all_users);
    }

    public static void editOtherProfileNow(String username) {
        User usercur = User.find("email_", username).first();
        render("Application/editProfile.html", usercur);
    }

    public static void deleteUser(String username) {

        User usercur = User.find("email_", Security.connected()).first();
        String mail = usercur.email_;
        User.delete("email_", username);

        
        if (mail.equals(username)) {
            redirect("/Secure/logout");
        } else {
            render("Application/deleteUser.html", username);

        }
    }
}
