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

package models.generate;

import utils.io.FileStringReader;
import utils.io.FileStringWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import models.Template;
import play.Play;
import utils.Substitution;
import utils.Zip;

/**
 *
 * @author Entwickler
 */
public class DocumentGenerator
{

	protected File templateFile;
	protected Map<String, String> keywordMap;
	private final static File file = new File(Play.applicationPath.getAbsolutePath() + "/public/tmp/");

	public DocumentGenerator(File templateFile, Map<String, String> keywordMap)
	{
		this.templateFile = templateFile;
		this.keywordMap = keywordMap;
	}

	private static synchronized File createUniqueFolder()
	{
		File folder = new File(file.getAbsolutePath() + "/" + System.currentTimeMillis());
		folder.mkdir();

		return folder;
	}

	private synchronized File createUniqueFile()
	{
		System.out.println(templateFile.getName());
		String filenames[] = templateFile.getName().split("_", 2);

		String filename;
		if (filenames.length == 1)
		{
			filename = filenames[0];
		}
		else
		{
			filename = filenames[1];
		}
		return new File(createUniqueFolder().getAbsolutePath() + "/" + filename);
	}

	private String readInTemplate() throws IOException
	{
		FileStringReader reader = new FileStringReader(templateFile);
		return reader.read();
	}

	private String replaceKeywords(String text)
	{
		Substitution substitution = new Substitution(text);
		substitution.replace(keywordMap);
		return substitution.getText();
	}

	private File saveGeneratedDocument(String text) throws IOException
	{
		File url = createUniqueFile();
		FileStringWriter writer = new FileStringWriter(url);
		writer.write(text);
		return url;
	}

	private String getExtension()
	{
		String filename = templateFile.getAbsolutePath();
		return filename.substring(filename.lastIndexOf(".") + 1);
	}

	private String getFilename() {
		String filename = templateFile.getAbsolutePath();
		return filename.substring(filename.lastIndexOf("/") + 1, filename.lastIndexOf("."));
	}

	public File create()
	{
		File document = null;
		try
		{
			String filename = getFilename();
			String extension = getExtension();
			if (extension.equalsIgnoreCase("docx"))
			{
				File uniqueFolder = createUniqueFolder();
				Zip zip = new Zip();
			
				zip.unzip(templateFile.getAbsolutePath(), uniqueFolder.getAbsolutePath() + "/");


				String path = uniqueFolder.getAbsolutePath() + "/" + filename;
				String docxContent = path + "/word";
				File file = new File(docxContent);
			
				try
				{
					for (File currentFile : file.listFiles())
					{
						if (currentFile.isDirectory())
						{
							continue;
						}
						FileStringReader reader = new FileStringReader(currentFile);
						String templatedText = reader.read();
						String replacedText = replaceKeywords(templatedText);

						FileStringWriter writer = new FileStringWriter(currentFile);
						writer.write(replacedText);
					}
					String downloadFileName = uniqueFolder.getAbsolutePath() + "/" + filename.substring(filename.indexOf("_") + 1);
					zip.zipDir(path + "/", downloadFileName + ".docx");
					document = new File(downloadFileName + ".docx");
				}
				catch (IOException iOException)
				{
					iOException.printStackTrace();
				}

				Template.deleteDir(new File(path));
			}
			else
			{
				String templateText = readInTemplate();
				String replacedText = replaceKeywords(templateText);
				document = saveGeneratedDocument(replacedText);
			}
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			fileNotFoundException.printStackTrace();
		}
		catch (IOException iOException)
		{
			iOException.printStackTrace();
		}
		return document;
	}
}
