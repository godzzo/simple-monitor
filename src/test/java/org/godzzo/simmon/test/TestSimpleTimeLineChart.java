package org.godzzo.simmon.test;

import org.godzzo.simmon.chart.SimpleTimeLineChart;
import org.junit.Test;

public class TestSimpleTimeLineChart {
	@Test
	public void generate() throws Exception {
		SimpleTimeLineChart chart = new SimpleTimeLineChart();
		
		chart.makeChart(
			"mem.free", 
			"out/sampler/base/hours/mem.free/2012/9/3/20.txt", 
			"out/sampler/base/hours/mem.free/2012/9/3/20.png"
		);
	}
}
