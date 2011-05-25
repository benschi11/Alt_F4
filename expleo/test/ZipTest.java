/*
 * Wildcard for License
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import models.generate.Document;
import models.generate.DocumentGenerator;
import org.junit.Test;
import play.Play;
import play.test.UnitTest;
import utils.io.FileStringWriter;

/**
 * Class description goes here.
 *
 * @author Robert Painsi <painsi@student.tugraz.at>
 * @version 1.00, May 25, 2011
 */
public class ZipTest extends UnitTest
{

	@Test
	public void testUnzip()
	{
		System.out.println("####################################\n\n\n");
		String filename = "1_Zip";
		String file = Play.applicationPath.getAbsolutePath() + "/data/test/" + filename + ".docx";
		try
		{
			FileInputStream fin = new FileInputStream(file);
			ZipInputStream zin = new ZipInputStream(fin);
			ZipEntry ze = null;
			while ((ze = zin.getNextEntry()) != null)
			{
				System.out.println("Unzipping " + ze.getName());
				String folders = Play.applicationPath.getAbsolutePath() + "/data/test/" + filename + "/";
				int index = ze.getName().lastIndexOf("/");
				if (index > 0)
				{
					folders += ze.getName().substring(0, index);
					File test = new File(folders);
					test.mkdirs();
				}

				FileOutputStream fout = new FileOutputStream(Play.applicationPath.getAbsolutePath() + "/data/test/" + filename + "/" + ze.getName());
				for (int c = zin.read(); c != -1; c = zin.read())
				{
					fout.write(c);
				}
				zin.closeEntry();
				fout.close();
			}
			zin.close();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Forrest Gump");

		DocumentGenerator generator = new DocumentGenerator(new File(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip/word/document.xml"), map);
		Document document = generator.create();

		FileStringWriter writer = new FileStringWriter(new File(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip/word/document.xml"));
		try
		{
			writer.write(document.getContent());
		}
		catch (IOException iOException)
		{
			iOException.printStackTrace();
		}

		try
		{
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip_o.docx"));
			zipDir(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip", zos);
			zos.close();
		}
		catch (IOException iOException)
		{
			iOException.printStackTrace();
		}

	}

	public void zipDir(String dir2zip, ZipOutputStream zos)
	{
		try
		{
			//create a new File object based on the directory we have to zip
			File zipDir = new File(dir2zip);
			//get a listing of the directory content
			String[] dirList = zipDir.list();
			byte[] readBuffer = new byte[2156];
			int bytesIn = 0;
			//loop through dirList, and zip the files
			for (int i = 0; i < dirList.length; i++)
			{
				File f = new File(zipDir, dirList[i]);
				if (f.isDirectory())
				{
					//if the File object is a directory, call this
					//function again to add its content recursively
					String filePath = f.getPath();
					zipDir(filePath, zos);
					//loop again
					continue;
				}
				//if we reached here, the File object f was not a directory
				//create a FileInputStream on top of f
				FileInputStream fis = new FileInputStream(f);
				// create a new zip entry
				String path = (f.getPath().split("1_Zip/"))[1];
				System.out.println(path);
				ZipEntry anEntry = new ZipEntry(path);
				//place the zip entry in the ZipOutputStream object
				zos.putNextEntry(anEntry);
				//now write the content of the file to the ZipOutputStream
				while ((bytesIn = fis.read(readBuffer)) != -1)
				{
					zos.write(readBuffer, 0, bytesIn);
				}
				//close the Stream
				fis.close();
			}
		}
		catch (Exception e)
		{
			//handle exception
		}
	}
}
