package com.market.test;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

public class ModifyFileTest {

	public static void main(String[] args) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("/Users/alex/Public/sg.txt", "rw");
	         String line = null;
            long lastPoint = 0; //记住上一次的偏移量
            long begin = 0;
            long end = 0;
            while ((line = raf.readLine()) != null) {
                final long ponit = raf.getFilePointer();
                String temp = new String(line.getBytes(), "UTF-8");
                if(temp.contains("Begin")) {
                		begin = ponit;
                }
                if(temp.contains("end")) {
                		end = ponit;
                }
                raf.seek(lastPoint);
                lastPoint = ponit; 
            }
            raf.seek(begin);
            raf.write("dhwgfejrkt煌上煌qyftwye\r\ngrijtoirehugwyfeghrjtykrhegwfydg".getBytes("UTF-8"));
		} catch (Exception e) {
			System.err.println(e);
		}finally {
			if(raf != null) {try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}}
		}
		
	}
	
	@Test
	public void test() {
		
		String s = "abcdefg";
		
		System.err.println(s.replace("abcdefg", "1234"));
		
	}
}
