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
 */

package utils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
