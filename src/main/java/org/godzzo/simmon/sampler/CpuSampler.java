package org.godzzo.simmon.sampler;

import org.hyperic.sigar.CpuPerc;

public class CpuSampler extends AbstractSigarSampler {
	private String type = "Combined";
	
	@Override
	public String getValue() throws Exception {
		CpuPerc cpuPerc = getSigar().getCpuPerc();
		
		double value = -1;
		
		if (getType().equalsIgnoreCase("Combined")) {
			value = cpuPerc.getCombined();
		} else if (getType().equalsIgnoreCase("Idle")) {
			value = cpuPerc.getIdle();
		} else if (getType().equalsIgnoreCase("Sys")) {
			value = cpuPerc.getSys();
		} else if (getType().equalsIgnoreCase("User")) {
			value = cpuPerc.getUser();
		} else {
			throw new RuntimeException("Type is unknown: "+getType());
		}
		
		return "" + value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
