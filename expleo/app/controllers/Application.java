package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.data.validation.Required;

public class Application extends Controller
{

  public static void index()
  {
     Template.deleteAll();

    Template firstTemplate = new Template("Temp1");
    Template secondTemplate = new Template("Temp2");

    firstTemplate.addCommand("Bubu");
    firstTemplate.addCommand("Lala");
    firstTemplate.addCommand("Slany");

    secondTemplate.addCommand("SlanyTime");

    firstTemplate.save();
    secondTemplate.save();
    
    List<Template> template_list = Template.findAll();

    System.out.println("Templatelist: "+template_list.isEmpty());
    System.out.println("item1: "+template_list.get(0));
    System.out.println("item2: "+template_list.get(1));

    render(template_list);
  }


  public static void selectedTemplate(Long id)
  {
    Template loadedTemplate = Template.findById(id);
 
    render(loadedTemplate);
  }

  public static void insertionComplete()
  {
    //Template template = Template.findById(id);

    Map<String,String[]> map = request.params.all();
    Template template = Template.findById(Long.decode(map.get("ID")[0]));

    template.doMap(map);

    Iterator iterator = template.templates_.keySet().iterator();

    while(iterator.hasNext())
    {
      String key = (String) iterator.next();
      System.out.println("Key: "+key+" value: "+ template.templates_.get(key));
    }

  }

}
