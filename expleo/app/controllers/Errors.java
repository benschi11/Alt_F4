/*
 *
 * Copyright (C) 2011 SW 11 Inc.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
import play.data.validation.Validation;
import play.mvc.results.Redirect;


public class Errors extends Controller
{

    @Before
    public static void setConnectedUser()
    {
        if (Security.isConnected())
        {
            User user = User.find("email_", Security.connected()).first();
            if (user != null)
            {
                renderArgs.put("user", user);
            }
        }
    }

    public static void displayError(long errorNumber, String errorString)
    {
        render(errorNumber, errorString);
    }

    public static void displayInlineError(long errorNumber, String errorString, String location)
    {
        render(errorString, location);
    }
}
