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
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Helper
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

    public static boolean isUtf8(String v)
    {
        byte bytearray[] = v.getBytes();
        CharsetDecoder d = Charset.forName("UTF-8").newDecoder();
        try
        {
            CharBuffer r = d.decode(ByteBuffer.wrap(bytearray));
            r.toString();
        }
        catch (CharacterCodingException e)
        {
            return false;
        }
        return true;
    }
    
    public static Boolean texToPdf(File tex, File dest)
    {
        ProcessBuilder texBuilder = new ProcessBuilder("pdflatex", tex.getAbsolutePath(), "-output-directory="+ dest.getAbsolutePath());
        texBuilder.redirectErrorStream(true);
        Process p;
        try
        {
            p = texBuilder.start();
        }
        catch (IOException ex)
        {
            System.out.println(ex.toString());
            return false;
        }
        String tmp = null;
        String error = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        System.out.println("latex wird kompiliert");

        try
        {
            while ((tmp = br.readLine()) != null)
            {
                System.out.println("In while");
                error = error + tmp + "\n";
            }

        }
        catch (IOException ioe)
        {
            System.out.println(ioe.toString());
        }
        System.out.println(error);

        try
        {
            p.waitFor();
        }
        catch (InterruptedException ex)
        {
            System.out.println(ex.toString());
        }
        if (p.exitValue() == 0)
        {
            System.out.println("Latex compilition successfull.");
            return true;
        }
        else
        {
            return false;
        }
    }
}
