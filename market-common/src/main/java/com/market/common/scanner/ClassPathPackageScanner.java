package com.market.common.scanner;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassPathPackageScanner {

	private String basePackage;
	
	private ClassLoader loader;
	
	public ClassPathPackageScanner(String basePackage) {
		this.basePackage = basePackage;
		this.loader = this.getClass().getClassLoader();
	}

	
	public List<String> scan(){
		List<String> nameList = new ArrayList<>();
		this.doScan(basePackage,nameList);
		return nameList;
	}
	
	
	private void doScan(String basePackage,List<String> nameList) {
		String splash = basePackage.replaceAll("\\.", "/");
		
		URL url = loader.getResource(splash);
		
		System.out.println("url -> " + url);
		if(url == null) {
			return;
		}
		
		String fileUrl = url.getFile();
		
		File file = new File(fileUrl);
		
		List<String> names = Arrays.asList(file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// 排除内部类
				return !name.contains("$");
			}
		}));
		for (String name : names) {
			if(name.endsWith(".class")) {
				StringBuilder sb = new StringBuilder(basePackage);
				sb.append(".");
				sb.append(name.substring(0, name.lastIndexOf(".")));
				nameList.add(sb.toString());
			}else{
				doScan(basePackage + "." + name, nameList);
			}
		}
	}
	
}
