package org.godzzo.io.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class TestZipOutputStream {
	@Test
	public void makeWithDir() throws Exception {
		File sampleFile = new File("out/sample-data.txt");
		FileUtils.writeStringToFile(sampleFile, "data");
		
		ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream("out/out.zip"));
		
		zipStream.putNextEntry(new ZipEntry("data/test.txt"));
		
		FileUtils.copyFile(sampleFile, zipStream);
		
		zipStream.closeEntry();
		zipStream.close();
	}
}
