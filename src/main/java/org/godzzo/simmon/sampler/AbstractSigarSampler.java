package org.godzzo.simmon.sampler;

import org.hyperic.sigar.Sigar;

abstract public class AbstractSigarSampler extends AbstractSampler {
	private Sigar sigar;

	public Sigar getSigar() {
		return sigar;
	}

	public void setSigar(Sigar sigar) {
		this.sigar = sigar;
	}
}
