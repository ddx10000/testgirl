package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DongDexuan
 * @date 2018-7-4 16:09
 * @desc
 */
public class RemoveDuplicate {

    public static void main(String[] args) throws IOException {
        if (args.length == 0){
            System.out.println("请输入文件名");
            return;
        }
        for (int z = 0; z < args.length; z++){
            File file = new File(args[z]);
            if (!file.exists()) {
                System.out.println(args[z] + "文件不存在！");
            }
            if (file.isDirectory()){
                List<File> fileList = new ArrayList<>();
                getDirectoryFile(file.getAbsolutePath(), fileList);
                for (File file1 : fileList){
                    remove(file1);
                }
            }else {
                remove(file);
            }
        }
    }

    private static void remove(File file) throws IOException {
        final String KEY = "k";
        Map<String, String> map = new HashMap<>();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String readLine = null;
            int i = 0;
            while ((readLine = bufferedReader.readLine()) != null) {
                // 每次读取一行数据，与 map 进行比较，如果该行数据 map 中没有，就保存到 map 集合中
                if (!map.containsValue(readLine)) {
                    map.put(KEY + i, readLine);
                    i++;
                }
            }
        }
        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write("");
            for (int j = 0; j < map.size(); j++) {
                fileWriter.append(map.get(KEY + j) + "\r\n");
            }
        }
    }

    private static void getDirectoryFile(String fileDir,List<File> fileList) {
        File file = new File(fileDir);
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        if (files == null) {// 如果目录为空，直接退出
            return;
        }
        // 遍历，目录下的所有文件
        for (File f : files) {
            if (f.isFile()) {
                fileList.add(f);
            } else if (f.isDirectory()) {
                System.out.println(f.getAbsolutePath());
                getDirectoryFile(f.getAbsolutePath(), fileList);
            }
        }
        for (File f1 : fileList) {
            System.out.println(f1.getName());
        }
    }
}
