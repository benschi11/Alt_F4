/*
 * Wildcard for License
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.text.Document;
import models.generate.DocumentGenerator;
import org.junit.After;
import org.junit.Test;
import play.Play;
import play.test.UnitTest;
import utils.Zip;
import utils.io.FileStringReader;
import utils.io.FileStringWriter;

/**
 * Class description goes here.
 *
 * @author Robert Painsi <painsi@student.tugraz.at>
 * @version 1.00, May 25, 2011
 */
public class ZipTest extends UnitTest
{

	private String zipPath = Play.applicationPath.getAbsolutePath() + "/test/testfiles/zip";
	private String zipFile = zipPath + "/ZipFile.zip";
	private String unzippedZipFile = zipPath + "/ZipFile/" + "ZipFile.txt";
	private String unzipFile = zipPath + "/UnzipFile";
	private String zippedFile = zipPath + "/UnzipFile.zip";

	@Test
	public void testZip()
	{
		Zip zip = new Zip();
		try
		{
			zip.zipDir(unzipFile, zippedFile);
		}
		catch (IOException iOException)
		{
			assertTrue("Couldn't zip file ("+ unzipFile +"", false);
		}
		File zippedFile = new File(this.zippedFile);
		assertTrue("Zipped file(" + zippedFile + ") not found!", zippedFile.exists());
	}
	

	@Test
	public void testUnzip()
	{
		Zip zip = new Zip();
		try
		{
			zip.unzip(zipFile, zipPath);
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			assertTrue("Couldn't find zip file(" + zipFile + ")", false);
		}
		catch (IOException iOException)
		{
			assertTrue("Couldn't unzip file(" + zipFile + ")", false);
		}

		File testFile = new File(unzippedZipFile);
		assertTrue("Unzipped file(" + testFile + ") not found!", testFile.exists());

		FileStringReader reader = new FileStringReader(testFile);
		String content = null;
		try
		{
			content = reader.read();
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			assertTrue("Unzipped file(" + testFile + ") doesn't exist.", false);
		}
		catch (IOException iOException)
		{
			assertTrue("IOException with file(" + testFile + ").", false);
		}

		assertEquals("Read content differs.", content, "Zip");
	}

	@After
	public void tearDown()
	{
		File unzippedZipFolder = new File(zipPath + "/ZipFile");
		File unzippedZipFile = new File(this.unzippedZipFile);
		if (unzippedZipFile.exists() && unzippedZipFolder.exists())
		{
			if (!unzippedZipFile.delete())
			{
				assertTrue("Couln't delete file(" + unzippedZipFile + ")", false);
			}
			if (!unzippedZipFolder.delete())
			{
				assertTrue("Couln't delete folder(" + unzippedZipFolder + ")", false);
			}
		}

		File zippedFile = new File(this.zippedFile);
		if (zippedFile.exists())
		{
			if (!zippedFile.delete())
			{
				assertTrue("Couln't delete file(" + zippedFile + ")", false);
			}
		}

	}

//	@Test
//	public void testUnzip2()
//	{
//	String filename = "1_Zip";
//	String file = Play.applicationPath.getAbsolutePath() + "/data/test/" + filename + ".docx";
//
//	Zip zip = new Zip();
//	try
//	{
//	zip.unzip(file, Play.applicationPath.getAbsolutePath() + "/data/test/");
//	}
//	catch (IOException iOException)
//	{
//	iOException.printStackTrace();
//	}
//
//	Map<String, String> map = new HashMap<String, String>();
//	map.put("name", "Forrest Gump");
//
//	DocumentGenerator generator = new DocumentGenerator(new File(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip/word/document.xml"), map);
////	Document document = generator.create();
//
////	FileStringWriter writer = new FileStringWriter(new File(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip/word/document.xml"));
////	try
////	{
////	writer.write(document.getContent());
////	}
////	catch (IOException iOException)
////	{
////	iOException.printStackTrace();
////	}
//
//	try
//	{
//	zip.zipDir(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip/",
//	Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip_asdjsad.docx");
//	//			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip_o.docx"));
//	//			zipDir(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip", zos);
//	//			zos.close();
//	}
//	catch (IOException iOException)
//	{
//	iOException.printStackTrace();
//	}
//	}
}
