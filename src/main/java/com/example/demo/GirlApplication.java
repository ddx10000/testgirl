package com.example.demo;

import com.example.demo.hack.HotSwapClassLoader;
import com.example.demo.me.Componet1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class GirlApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public Componet1 getComponet1() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		InputStream is = null;
		byte[] b = null;
		try {
			is = new FileInputStream("target/classes/com/example/demo/me/Componet1.class");
			b = new byte[is.available()];
			is.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		HotSwapClassLoader loader = new HotSwapClassLoader();
//		Class<Componet1> aClass = (Class<Componet1>) loader.loadClass("com.example.demo.me.Componet1");
//		Componet1 componet1 = aClass.newInstance();
//		Class clazz = loader.loadByte(b);
//		Componet1 componet1 = (Componet1) clazz.newInstance();
		return new Componet1();
	}

	public static void main(String[] args) {
		SpringApplication.run(GirlApplication.class, args);

	}
}
