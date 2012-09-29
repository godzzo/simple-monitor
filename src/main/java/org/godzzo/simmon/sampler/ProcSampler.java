package org.godzzo.simmon.sampler;

import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class ProcSampler extends AbstractSigarSampler {
	private String type = "Resident";
	private String proc = "ALL";
	
	@Override
	public String getValue() throws Exception {
		Sigar sigar = new Sigar();
		long value = 0;
		long[] pids = sigar.getProcList();

		for (long pid : pids) {
			ProcState state = null; 
			
			try {
				state = sigar.getProcState(pid);
			} catch (Exception e) {
				// Process exited!
			}

			if (state != null) {
				if (getProc().equals("ALL") || state.getName().equals(getProc())) {
					value += requestValue(sigar, value, pid, state);
				}
			}
		}
		
		return "" + value;
	}

	private long requestValue(Sigar sigar, long value, long pid, ProcState state)
			throws SigarException {
		if (getType().equalsIgnoreCase("Share")) {
			return sigar.getProcMem(pid).getShare();
		} else if (getType().equalsIgnoreCase("Resident")) {
			return sigar.getProcMem(pid).getResident();
		} else if (getType().equalsIgnoreCase("Size")) {
			return sigar.getProcMem(pid).getSize();
		} else if (getType().equalsIgnoreCase("MajorFaults")) {
			return sigar.getProcMem(pid).getMajorFaults();
		} else if (getType().equalsIgnoreCase("MinorFaults")) {
			return sigar.getProcMem(pid).getMinorFaults();
		} else if (getType().equalsIgnoreCase("PageFaults")) {
			return sigar.getProcMem(pid).getPageFaults();
		} else if (getType().equalsIgnoreCase("Threads")) {
			return state.getThreads();
		} else if (getType().equalsIgnoreCase("Nice")) {
			return state.getNice();
		} else if (getType().equalsIgnoreCase("Priority")) {
			return state.getPriority();
		} else if (getType().equalsIgnoreCase("Processor")) {
			return state.getProcessor();
		} else if (getType().equalsIgnoreCase("Process")) {
			return 1;
		} else {
			throw new RuntimeException("Type is unknown: "+getType());
		}
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProc() {
		return proc;
	}

	public void setProc(String proc) {
		this.proc = proc;
	}
}
