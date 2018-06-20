package com.example.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long> {

    private int end;
    private int start;
    private int threshold = 500000;

    CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (start > end){
            return 0L;
        }
        if (end - start < threshold) {
            long sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
        int middle = (start + end) / 2;
        CountTask left = new CountTask(start, middle);
        CountTask right = new CountTask(middle + 1, end);
        left.fork();
        right.fork();
        return left.join() + right.join();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = 0L;
        long usetime =0L;
        start = System.nanoTime();
        System.out.println(compute(0, 100000000));
        usetime = System.nanoTime() - start;
        System.out.println("for usetime:" + usetime);
        start = System.nanoTime();
//        ForkJoinTask<Long> submit = forkJoinPool.submit(new CountTask(0, 100000000));
        System.out.println(new CountTask(0, 100000000).compute());
        usetime = System.nanoTime() - start;
        System.out.println("forkjoin usetime:" + usetime);
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        CountTask countTask = new CountTask(0, 100);
//        ForkJoinTask<Integer> submit = forkJoinPool.submit(countTask);
//        System.out.println(submit.get());
//        if (countTask.isCompletedAbnormally()){
//            System.out.println(countTask.getException());
//        }
//        if (countTask.isCompletedNormally()){
//            System.out.println(countTask.get());
//        }
    }
    public static long compute(int start, int end){
        long sum = 0;
        for (int i = start; i <= end; i++){
            sum += i;
        }
        return sum;
    }
}
