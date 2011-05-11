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
}
