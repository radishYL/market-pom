package com.test.market;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import com.market.common.scanner.ClassPathPackageScanner;
import com.market.web.socket.MsgPushHandler;

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
		
		URL url = MsgPushHandler.class.getProtectionDomain().getCodeSource().getLocation();
		System.err.println(url.getPath());
		
	}
	
	@Test
	public void test02() throws FileNotFoundException, IOException {
		
		// 此方法会先从本项目开始查 查不到便去依赖的其它工程、JAR包查
		URL url = ClassLoader.getSystemResource("prop/sys.properties");
		
		System.err.println(url.getPath());
		
		Properties p = new Properties();
		
		// p.load(new FileInputStream(url.getPath()));
		
		// 此方法会先从本项目开始查 查不到便去依赖的其它工程、JAR包查
		p.load(ClassLoader.getSystemResourceAsStream("prop/sys.properties"));
		
		System.err.println(p.getProperty("jdbc.url"));
		
		System.err.println("=============================");
		
		// 本方法会查本项目、本项目依赖的其它工程、JAR包开始查
		Enumeration<URL> urls = ClassLoader.getSystemResources("prop/sys.properties");
		
		while (urls.hasMoreElements()) {
			URL u = urls.nextElement();
			System.err.println(u.getPath());
		}
		
	}
}
