/*
 * Wildcard for License
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import play.Play;

/**
 * Class description goes here.
 *
 * @author Robert Painsi <painsi@student.tugraz.at>
 * @version 1.00, May 25, 2011
 */
public class Zip
{

	private String root;

	private void zipDir(String dir2zip, ZipOutputStream zos) throws FileNotFoundException, IOException
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
			String path = (f.getPath().split(root))[1];
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

	public void zipDir(String directory, String output) throws IOException
	{
		root = directory;
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(output));
		zipDir(directory, zos);
		zos.close();
	}

	public void unzip(String input, String directory) throws FileNotFoundException, IOException
	{
		FileInputStream fin = new FileInputStream(new File(input));
		ZipInputStream zin = new ZipInputStream(fin);
		ZipEntry ze = null;
		while ((ze = zin.getNextEntry()) != null)
		{
			System.out.println("Unzipping " + ze.getName());
			String filename = input.substring(input.lastIndexOf("/"), input.lastIndexOf("."));
			String folders = directory + filename + "/";

			File zipEntryFolder = new File(directory + filename + "/");
			zipEntryFolder.mkdirs();

			int index = ze.getName().lastIndexOf("/");
			if (index > 0)
			{
				folders += ze.getName().substring(0, index);
				File test = new File(folders);
				test.mkdirs();
			}

			File zipEntryFile = new File(directory + filename + "/" + ze.getName());
			if (zipEntryFile.isDirectory())
			{
				continue;
			}

			FileOutputStream fout = new FileOutputStream(directory + filename + "/" + ze.getName());
			for (int c = zin.read(); c != -1; c = zin.read())
			{
				fout.write(c);
			}
			zin.closeEntry();
			fout.close();
		}
		zin.close();
	}
}
