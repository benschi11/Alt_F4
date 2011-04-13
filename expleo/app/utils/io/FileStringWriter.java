/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Entwickler
 */
public class FileStringWriter {

    private final File file;

    public FileStringWriter(File file) {
        this.file = file;
    }

    public void write(String text) throws IOException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file));
            writer.println(text);

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
