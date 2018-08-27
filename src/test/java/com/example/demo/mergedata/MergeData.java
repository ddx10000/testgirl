package com.example.demo.mergedata;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author DongDexuan
 * @date 2018/8/11 10:03
 * @desc
 */
public class MergeData {

    @Test
    public void test() throws IOException {
        File uid = new File("C:\\Users\\dongdx\\eclipse-workspace\\girl\\src\\test\\java\\com\\example\\demo\\mergedata\\uid.txt");
        File mergeall = new File("C:\\Users\\dongdx\\eclipse-workspace\\girl\\src\\test\\java\\com\\example\\demo\\mergedata\\mergeall.txt");
        File all = new File("C:\\Users\\dongdx\\eclipse-workspace\\girl\\src\\test\\java\\com\\example\\demo\\mergedata\\all.txt");
        try (RandomAccessFile uidR = new RandomAccessFile(uid, "r");
             RandomAccessFile mergeallR = new RandomAccessFile(mergeall, "r");
             RandomAccessFile allR = new RandomAccessFile(all, "rw")) {

            String mR;
            String uR;
            allR.write(("uid;text\r\n").getBytes());
            while ((mR = new String(mergeallR.readLine().getBytes("ISO-8859-1"), "utf-8"))
                    != null) {
                if (StringUtils.isEmpty(mR)) continue;
                if (StringUtils.isEmpty(uR = uidR.readLine())) {
                    uidR.seek(0);
                    uR = uidR.readLine();
                }
                allR.write((uR + ";" + mR + "\r\n").getBytes());
            }
        }
    }
}
