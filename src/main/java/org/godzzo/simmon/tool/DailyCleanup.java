package org.godzzo.simmon.tool;

import java.io.File;

import org.godzzo.simmon.util.file.ScanDayJob;
import org.godzzo.simmon.util.file.ZipHelper;

public class DailyCleanup extends ScanDayJob {
	@Override
	public void invokeDay(String name, File dayDir, File dataFile,
			File imageFile) throws Exception {

		if (dataFile.exists()) {
			ZipHelper.packFile(dataFile, true);
		}
	}
}
