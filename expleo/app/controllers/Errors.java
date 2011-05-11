/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import play.mvc.Controller;

/**
 *
 * @author Benedikt
 */
public class Errors extends Controller {
    
    public static void displayError(long errorNumber, String errorString)
    {
        render(errorNumber, errorString);
    }
    
    public static void displayInlineError(long errorNumber, String errorString, String location)
    {
        render(errorString, location);
    }

}
