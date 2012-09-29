package org.godzzo.simmon.chart;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.godzzo.simmon.util.file.DayHelper;
import org.godzzo.simmon.util.file.ScanFileNameJob;
import org.joda.time.DateTime;

public class MakeHourChart extends ScanFileNameJob {
	private Log log = LogFactory.getLog(MakeHourChart.class);

	private Chart chart;

	@Override
	public void invokeName(File file) throws Exception {
		String name = file.getName();

		DateTime now = DateTime.now();
		DateTime yesterday = now.minusDays(1);

		scanDayPath(name, now);
		scanDayPath(name, yesterday);
	}

	protected void scanDayPath(String name, DateTime base) throws Exception {
		String pathDay = DayHelper.makeDayPath(
				getSourcePath(), name, base).toString();
		File fileDay = new File(pathDay);

		if (fileDay.exists()) {
			File[] dataFiles = fileDay.listFiles();

			Arrays.sort(dataFiles);

			for (File dataFile : dataFiles) {
				if (dataFile.getName().endsWith(".txt")) {
					makeChart(name, dataFile);
				}
			}
		}
	}

	protected void makeChart(String name, File dataFile) throws IOException,
			Exception {
		File imageFile = getImage(dataFile);

		if (!imageFile.exists()
				|| dataFile.lastModified() > imageFile.lastModified()) {
			
			log.debug("CreateChart: " + imageFile.getCanonicalPath());

			getChart().makeChart(name, dataFile.getCanonicalPath(),
					imageFile.getCanonicalPath());
		}
	}

	protected File getImage(File dataFile) {
		String[] nameTags = dataFile.getName().split("\\.");

		String imagePath = dataFile.getParent() + File.separator + nameTags[0]
				+ ".png";

		File imageFile = new File(imagePath);
		return imageFile;
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}
}
