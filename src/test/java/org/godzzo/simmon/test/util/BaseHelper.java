package org.godzzo.simmon.test.util;

import java.io.IOException;

public class BaseHelper {
	public static void waitForEnter() throws IOException {
		int read;
		
		System.out.println("Press ENTER if you want to exit...");
		
		while ((read = System.in.read()) != 13 && read != 10) {
			System.out.println("Press ENTER if you want to exit: "+read);
		}
	}
}
