package org.godzzo.commons.io.test;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestMergeFiles {
	private Log log = LogFactory.getLog(getClass());
	
	@Test
	public void simple() throws Exception {
		String path = "src/test/resources/commons/io/4";
		
		File[] dataFiles = new File(path).listFiles();
		FileOutputStream outStream = new FileOutputStream(path+".txt");
		
		for (File dataFile : dataFiles) {
			if (dataFile.getName().endsWith(".txt")) {
				log.info("MERGE: "+dataFile.getAbsolutePath());
				
				FileUtils.copyFile(dataFile, outStream);
			}
		}
		
		outStream.close();
	}
}
