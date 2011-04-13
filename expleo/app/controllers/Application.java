package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

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
    List<String>values = new ArrayList<String>();

    Iterator iterator = loadedTemplate.getTemplates_().keySet().iterator();
    while(iterator.hasNext())
    {
     values.add(iterator.next().toString());
    }

    render(loadedTemplate, values);
  }

  public static void insertionComplete(Long id, List<String> values)
  {
    Template insertedTemplate = Template.findById(id);


    for(int i=0; i < values.size(); i++)
    {
      System.out.println("Wert "+i+" : "+values.get(i));
    }
    
  }

}
