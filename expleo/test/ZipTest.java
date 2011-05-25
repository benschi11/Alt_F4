/*
 * Wildcard for License
 */

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import models.generate.Document;
import models.generate.DocumentGenerator;
import org.junit.Test;
import play.Play;
import play.test.UnitTest;
import utils.Zip;
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
		String filename = "1_Zip";
		String file = Play.applicationPath.getAbsolutePath() + "/data/test/" + filename + ".docx";
		
		Zip zip = new Zip();
		try {
		zip.unzip(file, Play.applicationPath.getAbsolutePath() + "/data/test/");
		} catch (IOException iOException) {
			iOException.printStackTrace();
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
			zip.zipDir(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip/",
					  Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip_asdjsad.docx");
//			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip_o.docx"));
//			zipDir(Play.applicationPath.getAbsolutePath() + "/data/test/1_Zip", zos);
//			zos.close();
		}
		catch (IOException iOException)
		{
			iOException.printStackTrace();
		}
	}
}
