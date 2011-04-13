/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Entwickler
 */
public class Substitution {

    private String text;

    public Substitution(String text) {
        this.text = text;
    }

    public void replace(Map<String, Object> map) {
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry replacePairs = (Map.Entry) iterator.next();
            text = text.replaceAll(replacePairs.getKey().toString(), replacePairs.getValue().toString());
        }
    }

    public String getText() {
        return text;
    }
}
