package com.test.market;

import java.io.File;
import java.util.Random;

import org.junit.Test;

import com.market.web.netty.WebsocketServer;

public class NettyTest {

	public static void main(String[] args) throws InterruptedException {
		WebsocketServer server = new WebsocketServer();
		server.setPort(8899);
		server.start();
	}
	
	
	
	@Test
	public void test1() {
		
		System.err.println(System.getProperty("user.dir"));
		System.err.println(File.separator);
		
		File f = new File("/Users/alex/Public/market-pom/market-manager/market-manager-web/pom.xml");
		
		System.err.println(f.getName());
		
	}
	
	@Test
	public void test2() {
		int value = 0;
		int lamount = 6;
		int lnum = 100;
		
		if (lamount < lnum || lnum <= 0) {
			value =  0;
		}
		int min = 1;
		int max = lamount - lnum + 1;

		if (lnum == 1) {
			value =  lamount;
		}

		Random r = new Random(System.currentTimeMillis());
		float sum = 0;
		float[] ds = new float[lnum];
		for (int i = 0; i < lnum; i++) {
			ds[i] = Math.abs(r.nextFloat());
			sum += ds[i];
		}
		float val = (ds[0] / sum) * lamount;
		value = Math.round(val);

		if (value < min) {
			value = min;
		}
		if (value > max) {
			value = max;
		}
		System.err.println(value);
	}
}
