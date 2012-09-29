package org.godzzo.simmon.util.file;

import java.io.File;
import java.util.Arrays;

abstract public class ScanFileNameItemJob extends ScanFileNameJob {
	@Override
	public void invokeName(File file) throws Exception {
		File[] data = file.listFiles();
		String name = file.getName();

		Arrays.sort(data);

		for (File item : data) {
			invokeItem(name, item);
		}

		cleanUpByName();
	}

	abstract protected void invokeItem(String name, File item) throws Exception;
	
	protected void cleanUpByName() throws Exception {
	}
}
