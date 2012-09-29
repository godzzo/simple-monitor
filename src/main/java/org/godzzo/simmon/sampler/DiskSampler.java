package org.godzzo.simmon.sampler;


public class DiskSampler extends AbstractSigarSampler {
	private String type = "Used";
	private String disk;
	
	@Override
	public String getValue() throws Exception {
		return "";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}
}
