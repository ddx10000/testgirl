package com.example.demo;

import com.example.demo.testThis.Children;
import net.sf.ehcache.util.concurrent.ConcurrentHashMap;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author DongDexuan
 * @date 2018-6-30 15:24
 * @desc
 */
public class test3 {
    @Test
    public void test9() throws UnknownHostException, UnsupportedEncodingException {
        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress);
        InetAddress byAddress = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
        System.out.println(byAddress.getCanonicalHostName());

        String encode = URLEncoder.encode("jj", "GBK");
        System.out.println(encode);
        String decode = URLDecoder.decode(encode, "GBK");
        System.out.println(decode);
    }

    @Test
    public void test18() {

        String str = "DongDexuan";
        System.out.println(str.equalsIgnoreCase("Dongdexuan"));
    }

    @Test
    public void test19() {

        String str = "\\|DongDexuan";
        String[] split = str.split("\\|");
        System.out.println(str.equalsIgnoreCase("Dongdexuan"));
    }

    @Test
    public void test20() {
        String hourStr = "0";
        String minuteStr = "60";
        String secondStr = "500";

        long second = TimeUnit.HOURS.toSeconds(Integer.parseInt(hourStr))
                + TimeUnit.MINUTES.toSeconds(Integer.parseInt(minuteStr))
                + Integer.parseInt(secondStr);
        Integer[] result = new Integer[3];
        result[0] = Math.toIntExact(TimeUnit.SECONDS.toHours(second));
        result[1] = Math.toIntExact(TimeUnit.SECONDS.toMinutes(second) % 60);
        result[2] = Math.toIntExact(second % 60);
        System.out.println(Arrays.asList(result));
    }


    @Test
    public void test21() throws ParseException {
        String str = "2017:8:10|07:10:00";
//        String str = "|07:10:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:dd:MM|HH:mm:ss");
        Date date = simpleDateFormat.parse(str);
        System.out.println(date);
    }

    @Test
    public void test22() throws ParseException, IOException {
        System.out.println(ConcurrentTest.class.getResource(""));
        System.out.println(ConcurrentTest.class.getResource("/"));
        ConcurrentTest concurrentTest = new ConcurrentTest();
        System.out.println(concurrentTest.getClass().getClassLoader().getResources(""));
        System.out.println(concurrentTest.getClass().getClassLoader().getResources("/"));
        URL resource = ConcurrentTest.class.getResource("Test2");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.openStream()));
        String str = null;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }

    }

    @Test
    public void test23() throws ParseException, IOException {
        String a = "";
        assert !a.equals("") : "llll";
        ArrayList<String> strings = new ArrayList<>();
        strings.get(0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("dong");
        stringBuilder.append("de");
        stringBuilder.append("xuan");
        System.out.println(stringBuilder.length());
        System.out.println(stringBuilder);
        System.out.println(stringBuilder);

    }

    @Test
    public void test1() {

//        new Children().get1();
//        Object o = AopContext.currentProxy();
//        System.out.println(o);

        System.out.println(new Children().get());
    }

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        /*
         * 字符串转算术表达式
         */
        String str1 = "43*(2 + 1.4)+2*32/(3-2.1)";
        Object result1 = engine.eval(str1);
        System.out.println("结果类型:" + result1.getClass().getName() + ",计算结果:" + result1);
        /*
         * 字符串转条件表达式
         */
        int value = 7;
        String state = "正常";
        boolean flag = true;
        String st = "test";
        String str2 = "value > 5 && st == \"test\" && state == \"正常\" && flag == true";
        engine.put("value", value);
        engine.put("state", state);
        engine.put("flag", flag);
        engine.put("st", st);
        Object result2 = engine.eval(str2);
        System.out.println("结果类型:" + result2.getClass().getName() + ",结果:" + result2);
        Object result3 = engine.eval(str2);
        System.out.println("结果类型:" + result3.getClass().getName() + ",结果:" + result3);
    }

    public static class Deprecated {
        public static synchronized void main(String[] args) throws Exception {
// sleepThread不停的尝试睡眠
            Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
            sleepThread.setDaemon(true);
// busyThread不停的运行
            Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
            busyThread.setDaemon(true);
            sleepThread.start();
            busyThread.start();
// 休眠5秒，让sleepThread和busyThread充分运行
            TimeUnit.SECONDS.sleep(5);
            sleepThread.interrupt();
            busyThread.interrupt();
            System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
            System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
// 防止sleepThread和busyThread立刻退出
            TimeUnit.SECONDS.sleep(2);
        }

        static class SleepRunner implements Runnable {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        static class BusyRunner implements Runnable {
            @Override
            public void run() {
                while (true) {
                }
            }
        }
    }

    @Test
    public void test2() {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        reentrantLock.lock();
    }

    @Test
    public void test3() {
        String s = "0801127d123208dab40110011811284030dc9f043a0800000000000000004206c8f902000001480052060000000000005a060000000000001a25aa241500000000000104aa55004100000000070001000000000000640000640000000000b22a203030303031353131313030303830303038313735303331313132393930303030188ad5e9da05";
        System.out.println(s.length());
        byte[] b = s.getBytes();
        System.out.println(b.length);
        System.out.println(Arrays.toString(b));
        System.out.println(new String(b));

    }

    @Test
    public void test4() {
        String s = "08,01,12,7b,12,31,08,da,b4,01,10,01,18,11,28,40,30,f9,18,3a,08,00,00,00,00,00,00,00,00,42,06,ca,fa,02,00,00,01,48,00,52,06,00,00,00,00,00,00,5a,06,00,00,00,00,00,00,1a,24,aa,23,15,00,00,00,00,00,00,04,aa,55,00,41,00,00,00,00,06,00,01,00,00,00,00,00,00,64,00,00,5d,00,00,00,00,bc,2a,20,30,30,30,30,31,35,31,31,31,30,30,30,38,30,30,30,37,31,36,42,31,31,31,31,32,36,30,36,30,30,30,30,18,ad,a4,ea,da,05";
        System.out.println(s.length());
        byte[] b = getTransBytesByBasOrder(s);
        System.out.println(b.length);
        System.out.println(Arrays.toString(b));
        System.out.println(toHexString(b));
        System.out.println(new String(b));
        System.out.println(Arrays.toString(new String(b).getBytes()));
    }

    public static byte[] getTransBytesByBasOrder(String text) {
        String[] items = text.split(",");
        byte[] buf = new byte[items.length];
        for (int i = 0; i < items.length; i++) {
            buf[i] = (byte) (Integer.parseInt(items[i], 16));
        }
        return buf;
    }

    public static String toHexString(byte[] bytes) {

        if (bytes == null || bytes.length == 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {

            String hex = Integer.toHexString(b & 0xff);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            builder.append(hex);
            builder.append(",");
        }

        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private final ConcurrentHashMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();

    private String executionTask(final String taskName)
            throws ExecutionException, InterruptedException {
        while (true) {
            Future<String> future = taskCache.get(taskName);// 1.1,2.1
            if (future == null) {
                Callable<String> task = () -> taskName;
                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);// 1.3
                if (future == null) {
                    future = futureTask;
                    futureTask.run();// 1.4执行任务
                }
            }
            try {
                return future.get();// 1.5,2.2
            } catch (CancellationException e) {
                taskCache.remove(taskName, future);
            }
        }
    }

    @Test
    public void test5() {

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.submit(() -> "dong");

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(null, null);
        Long a = null;
        System.out.println("" + a);
    }


    static class MySingleton {
        private volatile static MySingleton mySingleton;

        private MySingleton() {
        }
        public MySingleton getMySingleton() {
            if (mySingleton == null) {
                synchronized (MySingleton.class) {
                    if (mySingleton == null){
                        mySingleton = new MySingleton();
                    }
                }
            }
            return mySingleton;
        }
    }

    static class MySingleton2 {
        private MySingleton2() {
        }
        private static class InnerClass{
            private static MySingleton2 mySingleton = new MySingleton2();
        }
        public MySingleton2 getMySingleton() {
            return InnerClass.mySingleton;
        }
    }
}
