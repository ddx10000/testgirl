package com.example.demo.hack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

@RestController
public class Hello2Controller {

	
	@GetMapping("/helloNew")
	public String helloNew() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		InputStream is = null;
		byte[] b = null;
		try {
			is = new FileInputStream("C:\\Users\\dongdx\\eclipse-workspace\\girl\\target\\classes\\com\\example\\demo\\me\\Test2.class");
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

//		System.out.println("<textarea style='width:1000;height=800'>");
//		System.out.println(JavaClassExecuter.execute(b));
//		System.out.println("</textarea>");
		String str = JavaClassExecuter.execute(b);
		return str;
	}
	
}
