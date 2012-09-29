package org.godzzo.simmon.sampler;

import org.hyperic.sigar.Swap;

public class SwapSampler extends AbstractSigarSampler {
	private String type = "Used";
	
	@Override
	public String getValue() throws Exception {
		Swap swap = getSigar().getSwap();
		long value = -1;
		
		if (getType().equalsIgnoreCase("Free")) {
			value = swap.getFree();
		} else if (getType().equalsIgnoreCase("PageIn")) {
			value = swap.getPageIn();
		} else if (getType().equalsIgnoreCase("PageOut")) {
			value = swap.getPageOut();
		} else if (getType().equalsIgnoreCase("Total")) {
			value = swap.getTotal();
		} else if (getType().equalsIgnoreCase("Used")) {
			value = swap.getUsed();
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
