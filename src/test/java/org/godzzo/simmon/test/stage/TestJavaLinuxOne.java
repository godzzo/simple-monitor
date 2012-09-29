package org.godzzo.simmon.test.stage;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:simmon/stage/java-linux-one.xml")
public class TestJavaLinuxOne {
	@BeforeClass
	public static void setUp() {
		String path = System.getProperty("java.library.path");
		
		System.out.println("PATH: "+path);
		
		path += File.pathSeparator + "lib" + File.separator + "sigar";
		
		System.setProperty("java.library.path", path);
	}
	
	@Test
	public void simple() throws Exception {
		int read;
		
		System.out.println("Press ENTER if you want to exit...");
		
		while ((read = System.in.read()) != 13) {
			System.out.println("Press ENTER if you want to exit: "+read);
		}
	}
}
