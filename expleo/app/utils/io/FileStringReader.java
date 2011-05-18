/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Entwickler
 */
public class FileStringReader {

    private final File file;
    private String data;

    public FileStringReader(File file) {
        this.file = file;
    }

    public String read() throws FileNotFoundException, IOException {
        if (data == null) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                StringBuilder builder = new StringBuilder();

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line).append('\n');
                }
                
                
                data = builder.toString();
                if (data.length() > 0) 
                {
                     data = data.substring(0, data.length()-1);
                }


            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
        }

        return data;
    }
}
