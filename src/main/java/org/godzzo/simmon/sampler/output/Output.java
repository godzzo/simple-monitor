package org.godzzo.simmon.sampler.output;

public interface Output {
	void write(String name, String line) throws Exception;

	void cleanUp() throws Exception;
}
