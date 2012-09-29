package org.godzzo.simmon.sampler.output;


public class ConsoleOutput implements Output {
	@Override
	public void write(String name, String line) throws Exception {
		System.out.printf("name: [%s], line: '%s'\n", name, line);
	}

	@Override
	public void cleanUp() throws Exception {
		System.out.println("Clean up :)");
	}
}
