package org.godzzo.simmon.sampler.output;

import org.godzzo.simmon.sampler.output.util.FileHandler;

public class SizedFileOutput extends FileOutput {
	private long biggerThan;
	
	@Override
	protected boolean isFilled(FileHandler handler) {
		return handler.size() > getBiggerThan();
	}

	public long getBiggerThan() {
		return biggerThan;
	}

	public void setBiggerThan(long biggerThan) {
		this.biggerThan = biggerThan;
	}
}
