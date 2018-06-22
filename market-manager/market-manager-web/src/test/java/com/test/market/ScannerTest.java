package com.test.market;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.market.common.scanner.ClassPathPackageScanner;

public class ScannerTest {

	public static void main(String[] args) {
		
		ClassPathPackageScanner scanner = new ClassPathPackageScanner("com.market.web");
		
		List<String> names = scanner.scan();
		
		for (String name : names) {
			
			System.err.println(name);
			
		}
		
	}
	
	@Test
	public void test01() throws URISyntaxException {
		
		URL url = ApplicationContext.class.getProtectionDomain().getCodeSource().getLocation();
		
		System.err.println(url.toURI());
		
	}
	
}
