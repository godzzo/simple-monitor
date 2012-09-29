package org.godzzo.simmon.test;

import java.io.File;

import org.godzzo.simmon.chart.MakeYesterdayChart;
import org.godzzo.simmon.chart.SimpleTimeLineChart;
import org.joda.time.DateTime;
import org.junit.Test;

public class TestMakeYesterdayChart {
	
	@Test
	public void invoke() throws Exception {
		MakeYesterdayChart make = new MakeYesterdayChart();
		
		make.setBegin(1);
		make.setTimes(1);
		
		make.setRemoveHours(true);
		
		make.setSourcePath("./data/collect");
		
		make.setChart(new SimpleTimeLineChart());
		
		make.setNow(new DateTime(2012, 9, 14, 0, 35));
		
		make.invokeName(new File("./data/collect/mem.free"));
	}
}
