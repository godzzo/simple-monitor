package org.godzzo.simmon.sampler;

import org.hyperic.sigar.Mem;

public class MemSampler extends AbstractSigarSampler {
	private String type = "Used";
	
	@Override
	public String getValue() throws Exception {
		Mem mem = getSigar().getMem();
		double value = -1;
		
		if (getType().equalsIgnoreCase("ActualFree")) {
			value = mem.getActualFree();
		} else if (getType().equalsIgnoreCase("ActualUsed")) {
			value = mem.getActualUsed();
		} else if (getType().equalsIgnoreCase("Free")) {
			value = mem.getFree();
		} else if (getType().equalsIgnoreCase("Ram")) {
			value = mem.getRam();
		} else if (getType().equalsIgnoreCase("Total")) {
			value = mem.getTotal();
		} else if (getType().equalsIgnoreCase("Used")) {
			value = mem.getUsed();
		} else if (getType().equalsIgnoreCase("FreePercent")) {
			value = mem.getFreePercent();
		} else if (getType().equalsIgnoreCase("UsedPercent")) {
			value = mem.getUsedPercent();
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
