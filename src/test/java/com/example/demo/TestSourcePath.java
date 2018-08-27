package com.example.demo;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author DongDexuan
 * @date 2018-6-21 15:40
 * @desc
 */
public class TestSourcePath {

    private static ClassLoader loader = Thread.currentThread().getContextClassLoader();

    @Test
    public void test() throws URISyntaxException, FileNotFoundException {
        System.out.println(new File(URI.create("")).getAbsolutePath());
        System.out.println(new File("").getAbsolutePath());
        System.out.println(new File(".").getAbsolutePath());
        System.out.println(new File("./").getAbsolutePath());
        System.out.println(new File("/").getAbsolutePath());
        //        new FileInputStream();
        String url = loader.getResource("").getPath();
        System.out.println(url);
        url = TestSourcePath.class.getResource("/").getPath();
        System.out.println(url);
        url = TestSourcePath.class.getResource("").getPath();
        System.out.println(url);
    }

    @Test
    public void test1() throws URISyntaxException {
        URI url = loader.getResource("application.properties").toURI();
        Path f = Paths.get(url);
        String result = f.toAbsolutePath().toString();
        System.out.println(result);
    }

}
