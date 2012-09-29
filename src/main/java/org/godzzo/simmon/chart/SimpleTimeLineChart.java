package org.godzzo.simmon.chart;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.godzzo.simmon.chart.util.FileTimeSeries;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class SimpleTimeLineChart implements Chart {
	private int width = 640;
	private int height = 480;
	
	@Override
	public void makeChart(String name, String dataFile, String chartFile) throws Exception {
		JFreeChart chart = createChart(name, createDataset(dataFile));
		
		BufferedImage image = chart.createBufferedImage(getWidth(), getHeight());
		
		ImageIO.write(image, "png", new File(chartFile));
	}

	private XYDataset createDataset(String dataFile) throws Exception {
		FileTimeSeries builder = new FileTimeSeries();

		builder.setFile(dataFile);
		builder.setTitle("Base");
		// builder.setFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		TimeSeriesCollection collection = new TimeSeriesCollection();
		collection.addSeries(builder.load());

		return collection;
	}

	private JFreeChart createChart(String name, XYDataset dataSet) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				name, 
				"Date", // Time Axis Label
				"Value", // Value Axis label
				dataSet, true, // legend
				true, // tooltips
				false // url
				);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setDomainPannable(true);
		plot.setRangePannable(false);
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		XYItemRenderer renderer = plot.getRenderer();

		if (renderer instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) renderer;
			xylineandshaperenderer.setBaseShapesVisible(false);
		}

		DateAxis dateaxis = (DateAxis) plot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));

		return chart;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
