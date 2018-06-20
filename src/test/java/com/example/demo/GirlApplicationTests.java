package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlApplicationTests {
	String dong = "dong";

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	@Test
	public void contextLoads() {
		Scanner scanner = new Scanner(System.in);
		int a = 100;
//		String a = "s";

	}


	@Override
	public String toString() {
		ArrayList<Object> arrayList = new ArrayList();
		for (Object o: arrayList) {
			
		}
		return super.toString();
	}
	
}
