/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Benedikt
 */
import java.io.*;

public class Copy
{

    public static void copy(File source, File dest) throws Exception
    {
        RandomAccessFile datei = new RandomAccessFile(source.getAbsolutePath(), "r");
        RandomAccessFile neudatei = new RandomAccessFile(dest.getAbsolutePath(), "rw");

        while (neudatei.length() < datei.length())
        {
            neudatei.write(datei.read());
        }

        datei.close();
        neudatei.close();
    }
}
