/*
 * COPYRIGHT INFORMATION
 * 
 * Developed by ALTernative + F4ntastic FOUR
 * 
 *  This program is free software; you can redistribute it and/or modify
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
 * 
 * 
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.generate;

import utils.io.FileStringReader;
import utils.io.FileStringWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import play.Play;
import utils.Substitution;

/**
 *
 * @author Entwickler
 */
public class DocumentGenerator {

    protected File templateFile;
    protected Map<String, String> keywordMap;
    private final File file = new File(Play.applicationPath.getAbsolutePath() + "/public/tmp/");

    public DocumentGenerator(File templateFile, Map<String, String> keywordMap) {
        this.templateFile = templateFile;
        this.keywordMap = keywordMap;
    }

    private synchronized File createUniqueFile() {
        
        File folder = new File(file.getAbsolutePath()+ "/" + System.currentTimeMillis());        
        folder.mkdir();
        
        String filename[] = templateFile.getName().split("_", 2);
        
        return new File(folder.getAbsolutePath() + "/"+ filename[1]);
    }

    private String readInTemplate() throws IOException {
        FileStringReader reader = new FileStringReader(templateFile);
        return reader.read();
    }

    private String replaceKeywords(String text) {
        Substitution substitution = new Substitution(text);
        substitution.replace(keywordMap);
        return substitution.getText();
    }

    private Document saveGeneratedDocument(String text) throws IOException {
        File url = createUniqueFile();
        FileStringWriter writer = new FileStringWriter(url);
        writer.write(text);
        return new Document(url, text);
    }

    public Document create() {
        try {
            String templateText = readInTemplate();
            String replacedText = replaceKeywords(templateText);
            Document document = saveGeneratedDocument(replacedText);

            return document;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
        return null;
    }
}
