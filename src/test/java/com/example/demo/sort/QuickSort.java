package com.example.demo.sort;

/**
 * @author DongDexuan
 * @date 2018/8/27 14:48
 * @desc
 */
public class QuickSort {

    public static void sort(int[] in){
        sort(in, 0, in.length - 1);
    }

    public static void sort(int[] in, int start, int end) {
        if (in.length < 2) {
            return;
        }
        int partition = partition(in, start, end);
        if (start < partition-1){
            sort(in, start, partition -1);
        }
        if (partition + 1 < end){
            sort(in, partition + 1, end);
        }
    }

    private static int partition(int[] in, int start, int end) {
        if (start >= end) {
            return start;
        }
        swap(in, start, end);
        int max = start;
        for (int index = start; index <= end - 1; index++) {
            if (in[index] < in[end]){
                swap(in, index, max++);
            }
        }
        swap(in, max, end);
        return max;
    }

    private static void swap(int[] in, int start, int end) {
        int temp = in[start];
        in[start] = in[end];
        in[end] = temp;
    }


    public static void main(String[] args) {
        int[] in = {6,2,9,0,3,8,1,7,5,4};
        sort(in);
        System.out.println(in);
    }
}
