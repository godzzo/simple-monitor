package org.godzzo.lang.test;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestUserHome {
	private static Log log = LogFactory.getLog(TestUserHome.class);
	
	/*
	 * changing-the-current-working-directory - There is no way to do this in Java!
	 * http://stackoverflow.com/questions/840190/changing-the-current-working-directory-in-java
	 * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4045688 - Will Not Fix!
	 * Only in JNI...
	 */
	
	@BeforeClass
	public static void setUp() throws Exception {
		log.info("before user.home: "+System.getProperty("user.home"));
		System.setProperty("user.home", new File("./data").getCanonicalPath());
		log.info("after user.home: "+System.getProperty("user.home"));
	}
	
	@Test
	public void writeFile() throws Exception {
		File file = new File("test-file.txt");
		
		log.info("Current File: "+file.getCanonicalPath());
	}
}
