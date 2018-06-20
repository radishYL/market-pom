package com.test.market;

import java.util.List;

import com.market.common.scanner.ClassPathPackageScanner;

public class ScannerTest {

	public static void main(String[] args) {
		
		ClassPathPackageScanner scanner = new ClassPathPackageScanner("com.market.common");
		
		List<String> names = scanner.scan();
		
		for (String name : names) {
			
			System.err.println(name);
			
		}
		
	}
	
}
