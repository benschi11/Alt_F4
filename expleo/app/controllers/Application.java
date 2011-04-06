package controllers;

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

  public void selectTemplate()
  {
    Template firstTemplate = new Template("Temp1");
    render(firstTemplate);
  }
}
