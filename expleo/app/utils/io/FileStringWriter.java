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
 * 
 * 
 * 
 */

/*
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
