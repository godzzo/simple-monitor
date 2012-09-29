package org.godzzo.simmon;

import java.io.File;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Man {
	public static void main(String[] args) throws Exception {
		String configPath = "simmon/full-base.xml";
		String libPath = File.pathSeparator + "lib" + File.separator + "sigar";
		
		if (args.length>0) {
			configPath = args[0];
		}
		if (args.length>1) {
			libPath = args[1];
		}
		
		setLibPath(libPath);
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configPath);
		
		context.registerShutdownHook();
		
		int read;
		
		System.out.println("Press ENTER if you want to exit...");
		
		while ((read = System.in.read()) != 13) {
			System.out.println("Press ENTER if you want to exit: "+read);
		}
		
	}

	private static void setLibPath(String libPath) {
		String path = System.getProperty("java.library.path");
		System.out.println("PATH: "+path);
		path += libPath;
		System.setProperty("java.library.path", path);
	}
}
