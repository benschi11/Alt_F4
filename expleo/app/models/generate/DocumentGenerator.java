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
import utils.Substitution;

/**
 *
 * @author Entwickler
 */
public class DocumentGenerator {

    protected File templateFile;
    protected Map<String, String> keywordMap;
    private final File file = new File("expleo/public/tmp/");

    public DocumentGenerator(File templateFile, Map<String, String> keywordMap) {
        this.templateFile = templateFile;
        this.keywordMap = keywordMap;
    }

    private File createUniqueFile() {
        return new File(file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".tec");
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
