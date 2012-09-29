package org.godzzo.simmon.util.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

public class ZipHelper {
	public static void packFile(File dataFile, boolean remove) throws FileNotFoundException,
			IOException {
		
		ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(
				dataFile.getCanonicalPath() + ".zip"));

		zipStream.putNextEntry(new ZipEntry(dataFile.getName()));

		FileUtils.copyFile(dataFile, zipStream);

		zipStream.closeEntry();

		zipStream.close();

		if (remove) {
			dataFile.delete();
		}
	}

}
