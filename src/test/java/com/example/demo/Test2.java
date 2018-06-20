package com.example.demo;

import org.junit.Test;

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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {

    @Test
    public void test(){
        ReentrantLock reentrantLock = new ReentrantLock();
        System.out.println("finished");
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = (Calendar) instance.clone();
        System.out.println(instance);
        System.out.println(instance.get(Calendar.DATE));
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.HOUR_OF_DAY, 13);
        instance.set(Calendar.DATE, 18);
        System.out.println(instance.get(Calendar.DATE));
        System.out.println(instance);
        long time = instance.getTimeInMillis() - instance2.getTimeInMillis();
        System.out.println(time);
        System.out.println(TimeUnit.DAYS.toDays(1));
        System.out.println(TimeUnit.MILLISECONDS.toHours(time));
    }

    @Test
    public void test1(){

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("dong", "dexuan");

        final HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("dong","dexuan");
        stringStringHashMap.put(null,null);
        Iterator<Map.Entry<String, String>> iterator = stringStringHashMap.entrySet().iterator();
//        while (iterator.hasNext()){
//
//            Map.Entry<String, String> next = iterator.next();
//            iterator.remove();
//        }

        stringStringHashMap.forEach((key, value) -> {
            System.out.println(key + value);
            stringStringHashMap.remove(key);
        });
        for (HashMap.Entry<String,String> entry : stringStringHashMap.entrySet()){
            System.out.println(entry);
        }
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("a",1);
        concurrentHashMap.put("b",1);
        concurrentHashMap.put("c",1);
//        concurrentHashMap.put("d",1);
        concurrentHashMap.put("e",1);
        concurrentHashMap.put("f",1);
        concurrentHashMap.put("g",1);
        concurrentHashMap.put("h",1);
        concurrentHashMap.put("i",1);
        concurrentHashMap.put("g",1);
        concurrentHashMap.put("k",1);
        concurrentHashMap.put("l",1);
        concurrentHashMap.put("m",1);
        concurrentHashMap.put("n",1);
//        concurrentHashMap.forEachEntry();
//        concurrentHashMap.forEach((s, integer) -> {
//            System.out.println(s + " " + integer);
//            concurrentHashMap.remove(s);
//        });
        concurrentHashMap.forEachKey(1,s -> {
                    System.out.println(s);
                    concurrentHashMap.remove(s);
                }
                );
        Thread thread = new Thread(() -> System.out.println(concurrentHashMap.get("d")));
        Thread thread2 = new Thread(() -> concurrentHashMap.put("d",3));
        thread.start();
        thread2.start();
        System.out.println("finished!");
    }

    @Test
    public void test2(){
        List<String> device = new ArrayList<String>();
        device.add("dong");
        device.add("dong");
        device.add("dong");
        String str = device.toString();
        str = str.replace("[","").replace("]","");
        System.out.println(str);
        Integer integer = 15;
        String s = Integer.toHexString(integer).toUpperCase();
        System.out.println(s);
        System.out.println(String.format("dong%s","dexuan"));
    }

    @Test
    public void test3() throws InterruptedException {
        ConcurrentLinkedDeque<String> strings = new ConcurrentLinkedDeque<>();
        strings.offer("dong");
        strings.add("de");

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(1);
//        new Thread(() -> {
//            try {
//                arrayBlockingQueue.put("dong");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
        arrayBlockingQueue.put("dong");
        long start = System.currentTimeMillis();
        new Thread(() -> {
            try {
                arrayBlockingQueue.offer("de", 1, TimeUnit.MINUTES);
                long end = System.currentTimeMillis();
                System.out.println("de offer successed " + (end - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        arrayBlockingQueue.offer("de", 2, TimeUnit.MINUTES);
        Thread.sleep(200);
        arrayBlockingQueue.poll();
        long end = System.currentTimeMillis();
        System.out.println("take success "+ (end - start));
//        arrayBlockingQueue.poll();
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("de");
//        arrayList.get(0);
//        arrayBlockingQueue.remove(0);
    }

    @Test
    public void test4(){
        String name = "电饭煲零零一零三";
        String[] numStr = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] num = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String nameNew = name;
        for (int i = 0; i < numStr.length; i++){
            nameNew = nameNew.replace(numStr[i], num[i]);
        }
        System.out.println(nameNew);
        System.out.println(name);
        StringBuilder stringBuilder = new StringBuilder(name);
        for (int i = 0; i < numStr.length; i++){
            stringBuilder = stringBuilder.replace(i, i, "");
        }

    }

    @Test
    public void test5() throws InterruptedException {
//        DelayQueue delayQueue = new DelayQueue<>();
//        ScheduledFutureTask
//        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
        for (int i = 0; i < 5; i ++){
            final int temp = i + 1;
            pool.schedule(() -> {
                System.out.println("第"+temp+"个炸弹爆炸时间:" + simpleDateFormat.format(new Date()));
            }, temp * 1, TimeUnit.SECONDS);
        }
        pool.shutdown();
//        Thread.sleep(10000);
        System.out.println("end main时间:" + simpleDateFormat.format(new Date()));
    }


    @Test
    public void test6() throws InterruptedException {
        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                linkedTransferQueue.tryTransfer("try dong");
                linkedTransferQueue.transfer("dong");
                System.out.println("dong finshed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                String ta = linkedTransferQueue.take();
                System.out.println("take finshed " + ta);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        Thread.sleep(2000);
//        linkedTransferQueue.tryTransfer("de");
        System.out.println("finished");
    }

    @Test
    public void test7() throws InterruptedException {
        LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<>(2);
        linkedBlockingDeque.put("dong");
        linkedBlockingDeque.take();

    }

//    public static void main(String[] args) throws InterruptedException {
//        Test2 test2 = new Test2();
//        test2.test6();
//        System.out.println("main finished");
//    }

    @Test
    public void test8(){
//        AtomicInteger integer = new AtomicInteger(1);
//        System.out.println(integer.addAndGet(2));
//        System.out.println(integer.compareAndSet(4,5));
//        System.out.println(integer.get());
//        integer.lazySet(6);
//        System.out.println(integer.getAndSet(8));
//        System.out.println(integer.get());
//
//        integer.getAndIncrement();
//
//        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
//        atomicBoolean.getAndSet(true);
        double d = 88.88;
        long l = Math.round(d);
        System.out.println(l);

        long ll = 100L;
        double dd = (double) ll;
        System.out.println(dd);

        new Long(10000000000L);
        String binaryString = Long.toHexString(10000000000L);
        System.out.println(binaryString);
        System.out.println(Long.parseLong(binaryString, 16));
        System.out.println(Float.floatToIntBits(0.1f));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(0.1f)));
        System.out.println(Float.floatToIntBits(0.01f));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(0.01f)));
        System.out.println(Float.floatToRawIntBits(0.01f));
        System.out.println(Float.intBitsToFloat(1008981770));
        System.out.println(Double.doubleToLongBits(0.01));
        System.out.println(Double.longBitsToDouble(4576918229304087675L));

        int[] a = new int[]{1,2};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(a);
        System.out.println(atomicIntegerArray.addAndGet(0,4));
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(a[0]);

        AtomicReference atomicReference = new AtomicReference(new Double(0.1));
        AtomicIntegerFieldUpdater<Test2> old = AtomicIntegerFieldUpdater.newUpdater(Test2.class, "old");
        Test2 test2 = new Test2();
        System.out.println(old.addAndGet(test2,3));

    }

    public volatile int old;

    @Test
    public void test10() throws InterruptedException, ExecutionException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        final Future<String> future = executorService.submit(() -> {
            long end = System.nanoTime();
            cyclicBarrier.await();
            long usetime = System.nanoTime() - end;
            System.out.println(Thread.currentThread().getName() + " useTime:" + usetime + " current time " + simpleDateFormat.format(new Date(end / 1000000)) + "." + end % 1000000);
            countDownLatch.countDown();
            return "haha";
        });
        Future<String> future1 = executorService.submit(() -> {
            long start = System.nanoTime();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cyclicBarrier.await();
            long end = System.nanoTime();
            long usetime = end - start;
            System.out.println(Thread.currentThread().getName() + " sleep thread useTime:" + usetime + " current time " + simpleDateFormat.format(new Date(end / 1000000)) + "." + end % 1000000);
            countDownLatch.countDown();
            return "dong";
        });

        long l = System.nanoTime();
        countDownLatch.await();
//        System.out.println(future.get());
//        System.out.println(future1.get());
        long end = System.nanoTime();
        long usetime = end - l;
        System.out.println(Thread.currentThread().getName() + " main useTime:" + usetime  + " current time " + simpleDateFormat.format(new Date(end / 1000000)) + "." + end % 1000000);
        System.out.println(simpleDateFormat.format(new Date(System.currentTimeMillis())));
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
    }

    @Test
    public void test11(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread thread = new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
            }
        });
        thread.start();
        thread.interrupt();
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(cyclicBarrier.isBroken());
        }
    }

    @Test
    public void test12(){
        final int THREAD_NUMBER = 30;
        final int SEMAPHONE_NUMBER = 1;
        Semaphore semaphore = new Semaphore(SEMAPHONE_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBER);
        for (int i = 0; i < THREAD_NUMBER; i ++){
            final int k = i;
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("dddddddd " + k);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Test
    public void test13(){
        Exchanger<String> stringExchanger = new Exchanger<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final Future<?> submit = executorService.submit(() -> {
            String a = "银行账户A";
            try {
                stringExchanger.exchange(a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            String b = "银行账户B";
            try {
                String exchange = stringExchanger.exchange(b);
                if (!b.equals(exchange)){
                    System.out.println(exchange + " not equal " + b);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
        List<Runnable> runnables = executorService.shutdownNow();
        new Thread();

    }

    @Test
    public void test14(){
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.execute(() -> {
//            System.out.println("dong");
//        });
//        executorService.execute(() -> {
//            System.out.println("dong");
//        });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>());
        Future<?> submit = threadPoolExecutor.submit(() -> {
            System.out.println("dong");
            System.out.println("dong");
            System.out.println("dong");
            System.out.println("dong");
            System.out.println("dong");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("de");
        });
        submit.cancel(true);
        threadPoolExecutor.execute(() -> {
            System.out.println("de");
        });
        threadPoolExecutor.execute(() -> {
            System.out.println("xuan");
        });
        System.out.println(threadPoolExecutor.getTaskCount());
        System.out.println(threadPoolExecutor.getCompletedTaskCount());
        System.out.println(threadPoolExecutor.getLargestPoolSize());
        System.out.println(threadPoolExecutor.getPoolSize());
        System.out.println(threadPoolExecutor.getActiveCount());

        System.out.println(threadPoolExecutor.getCorePoolSize());

        ExecutorService executorService1 = Executors.newCachedThreadPool();
        executorService1.execute(() -> {
            System.out.println("dong");
        });
        executorService1.execute(() -> {
            System.out.println("de");
        });
        executorService1.execute(() -> {
            System.out.println("xuan");
        });
//        executorService1.
        Executors.newSingleThreadExecutor();
        Executors.newScheduledThreadPool(2);
        Executors.newWorkStealingPool();
        Executors.newSingleThreadScheduledExecutor();
//        new ThreadPoolExecutor();
    }

    @Test
    public void test15() throws InterruptedException {//TODO
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//                System.out.println("haha");
//            }
//        }, 2000, 1000);
//        timer.cancel();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ScheduledThreadPoolExecutor scheduledExecutorService = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.schedule(() -> {
//            System.out.println("haha!");
//            countDownLatch.countDown();
//        },1,TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("haha!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        },3,1,TimeUnit.SECONDS);
//        scheduledExecutorService.scheduleWithFixedDelay(() -> {
//            System.out.println("haha!");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            countDownLatch.countDown();
//        },3,1,TimeUnit.SECONDS);
//        Runnable take = scheduledExecutorService.getQueue().take();
//        System.out.println(take);
//        Thread.sleep(4000);
//        System.out.println("finished");
//        scheduledExecutorService.submit(take);
        countDownLatch.await();
//        countDownLatch.countDown();
        scheduledExecutorService.shutdown();
    }


    public static void main(String[] args) throws InterruptedException {
//        new Thread(() -> {
//            System.out.println("hh");
//        }).start();
//        new Test2().test15();
        Thread thread = new Thread(() -> {
            System.out.println("dong");
        });
        thread.start();
        thread.join();
        System.out.println("thread finished");
        thread.start();
        System.out.println("finished");
    }

    @Test
    public void test17() throws InterruptedException {
        DelayQueue<Delayed> delayeds = new DelayQueue<>();
        delayeds.put(null);
        delayeds.take();
    }

    @Test
    public void test16() throws InterruptedException {
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("dong","dong");
        concurrentHashMap.put("xuan","xuan");
        concurrentHashMap.put("de","de");
        concurrentHashMap.put("haha","haha");
        concurrentHashMap.forEach((s, s2) -> {
            if (s.equals("xuan"))
            concurrentHashMap.remove(s);
        });
        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        strings.add("dong");
        strings.add("de");
        strings.add("dong");
        strings.add("xuan");
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        executorService.submit(() -> {
            strings.forEach(s -> {
                System.out.println(s);
                if (s.equals("dong")){
                    strings.remove("dong");
                    strings.add("aa");
                }
            });
            countDownLatch.countDown();
        });
        executorService.submit(() -> {
            strings.forEach(s -> {
                System.out.println(s);
                if (s.equals("dong")){
                    strings.remove("dong");
                    strings.add("bb");
                }
            });
            countDownLatch.countDown();
        });
        System.out.println("-------------");
        countDownLatch.await();
        strings.forEach(s -> {
            System.out.println("== " + s);
        });

    }

    @Test
    public void test9() throws UnknownHostException, UnsupportedEncodingException {
        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress);
        InetAddress byAddress = InetAddress.getByAddress(new byte[]{127,0,0,1});
        System.out.println(byAddress.getCanonicalHostName());

        String encode = URLEncoder.encode("jj", "GBK");
        System.out.println(encode);
        String decode = URLDecoder.decode(encode, "GBK");
        System.out.println(decode);
    }

    @Test
    public void test18(){

        String str = "DongDexuan";
        System.out.println(str.equalsIgnoreCase("Dongdexuan"));
    }

    @Test
    public void test19(){

        String str = "\\|DongDexuan";
        String[] split = str.split("\\|");
        System.out.println(str.equalsIgnoreCase("Dongdexuan"));
    }

    @Test
    public void test20(){
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
        System.out.println(Test2.class.getResource(""));
        System.out.println(Test2.class.getResource("/"));
        Test2 test2 = new Test2();
        System.out.println(test2.getClass().getClassLoader().getResources(""));
        System.out.println(test2.getClass().getClassLoader().getResources("/"));
        URL resource = Test2.class.getResource("Test2");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.openStream()));
        String str = null;
        while((str = br.readLine()) != null) {
            System.out.println(str);
        }

    }

}
