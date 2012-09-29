package org.godzzo.simmon.sampler;

public class JvmSampler extends AbstractSampler {
	private String type = "Combined";

	@Override
	public String getValue() throws Exception {
		Runtime runtime = Runtime.getRuntime();
		String value;
		
		if (getType().equals("Used")) {
			value = "" + (runtime.totalMemory() - runtime.freeMemory());
		} else if (getType().equals("Free")) {
			value = "" + runtime.freeMemory();
		} else if (getType().equals("Total")) {
			value = "" + runtime.totalMemory();
		} else if (getType().equals("Max")) {
			value = "" + runtime.maxMemory();
		} else {
			throw new RuntimeException("Type is unknown: "+getType());
		}
		
		return value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
