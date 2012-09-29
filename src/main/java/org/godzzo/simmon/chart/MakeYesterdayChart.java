package org.godzzo.simmon.chart;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.godzzo.simmon.util.file.ScanDayJob;

public class MakeYesterdayChart extends ScanDayJob {
	private Log log = LogFactory.getLog(MakeYesterdayChart.class);
	private boolean removeHours = true;
	private Chart chart;

	@Override
	public void invokeDay(String name, File dayDir, File dataFile,
			File imageFile) throws Exception {

		boolean noImage = !imageFile.exists();
		boolean hasHours = (dayDir.exists() && dayDir.list().length > 0);
		boolean hasDataButNoImage = (noImage && dataFile.exists());

		if (hasHours || hasDataButNoImage) {
			if (noImage || findNewer(dayDir, imageFile)) {
				collectData(dayDir, dataFile);

				makeChart(name, dataFile, imageFile);
			}
		}
	}

	protected void makeChart(String name, File dataFile, File imageFile)
			throws Exception {
		getChart().makeChart(name, dataFile.getCanonicalPath(),
				imageFile.getCanonicalPath());
	}

	protected void collectData(File dayDir, File outFile) throws Exception {
		File[] dataFiles = dayDir.listFiles();
		
		if (dataFiles != null) {
			FileOutputStream outStream;
	
			if (isRemoveHours()) {
				outStream = new FileOutputStream(outFile, true);
			} else {
				outStream = new FileOutputStream(outFile);
			}
	
			Arrays.sort(dataFiles);
	
			for (File dataFile : dataFiles) {
				if (dataFile.getName().endsWith(".txt")) {
					log.debug("Merge data file: " + dataFile.getCanonicalPath());
					FileUtils.copyFile(dataFile, outStream);
				}
			}
	
			outStream.close();
	
			removeHours(dataFiles);
		}
	}

	private void removeHours(File[] dataFiles) throws Exception {
		if (isRemoveHours()) {
			for (File dataFile : dataFiles) {
				if (dataFile.getName().endsWith(".txt")
						|| dataFile.getName().endsWith(".png")) {
					log.debug("Delete hour data file: "
							+ dataFile.getCanonicalPath());
					dataFile.delete();
				}
			}
		}
	}

	private boolean findNewer(File dayDir, File imageFile) {
		File[] dataFiles = dayDir.listFiles();

		if (dataFiles != null) {
			for (File dataFile : dataFiles) {
				if (dataFile.lastModified() > imageFile.lastModified()) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean isRemoveHours() {
		return removeHours;
	}

	public void setRemoveHours(boolean removeHours) {
		this.removeHours = removeHours;
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}
}
