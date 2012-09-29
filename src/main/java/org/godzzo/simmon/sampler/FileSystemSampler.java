package org.godzzo.simmon.sampler;

import org.hyperic.sigar.FileSystemMap;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;

public class FileSystemSampler extends AbstractSigarSampler {
	private String type = "Avail";
	private String drive = "C:\\";
	
	private String dirName;
	
	private String getDirName() throws Exception {
		if (dirName == null) {
			Sigar sigar = new Sigar();
	
			FileSystemMap fileSystemMap = sigar.getFileSystemMap();
	
			setDirName(fileSystemMap.getFileSystem(getDrive()).getDirName());
		}
		
		return dirName;
	}
	
	@Override
	public String getValue() throws Exception {
		double value = -1;
		
		FileSystemUsage usage = getSigar().getFileSystemUsage(getDirName());

		if (getType().equalsIgnoreCase("Avail")) {
			value = usage.getAvail();
		} else if (getType().equalsIgnoreCase("Used")) {
			value = usage.getUsed();
		} else if (getType().equalsIgnoreCase("DiskQueue")) {
			value = usage.getDiskQueue();
		} else if (getType().equalsIgnoreCase("DiskServiceTime")) {
			value = usage.getDiskServiceTime();
		} else if (getType().equalsIgnoreCase("UsePercent")) {
			value = usage.getUsePercent();
		} else if (getType().equalsIgnoreCase("DiskReadBytes")) {
			value = usage.getDiskReadBytes();
		} else if (getType().equalsIgnoreCase("DiskReads")) {
			value = usage.getDiskReads();
		} else if (getType().equalsIgnoreCase("DiskWriteBytes")) {
			value = usage.getDiskWriteBytes();
		} else if (getType().equalsIgnoreCase("DiskWrites")) {
			value = usage.getDiskWrites();
		} else if (getType().equalsIgnoreCase("Files")) {
			value = usage.getFiles();
		} else if (getType().equalsIgnoreCase("Free")) {
			value = usage.getFree();
		} else if (getType().equalsIgnoreCase("FreeFiles")) {
			value = usage.getFreeFiles();
		} else if (getType().equalsIgnoreCase("Total")) {
			value = usage.getTotal();
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

	public String getDrive() {
		return drive;
	}

	public void setDrive(String drive) {
		this.drive = drive;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
}
