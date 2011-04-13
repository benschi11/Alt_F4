/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models.generate;

import java.io.File;

/**
 *
 * @author Entwickler
 */
public class Document {

    private final File file;
    private final String content;

    public Document(File file, String content) {
        this.file = file;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public File getFile() {
        return file;
    }
}
