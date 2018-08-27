package com.example.demo.sort;

/**
 * @author DongDexuan
 * @date 2018/8/27 16:17
 * @desc
 */
public class HeapSort {

    public static void sort(int[] in) {
        int[] inTemp = new int[in.length];
        sort(in, inTemp, 0, in.length - 1);
    }

    private static void sort(int[] in, int[] inTemp, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (start + end) / 2;
        if (start < middle){
            sort(in, inTemp, start, middle);
        }
        if (middle + 1 < end){
            sort(in, inTemp, middle + 1, end);
        }
        int index1 = start;
        int index2 = middle + 1;
        int index = start;
        while (index1 <= middle && index2 <= end) {
            if (in[index1] < in[index2]) {
                inTemp[index++] = in[index1++];
            } else {
                inTemp[index++] = in[index2++];
            }
        }
        while (index1 <= middle) {
            inTemp[index++] = in[index1++];
        }
        while (index2 <= end) {
            inTemp[index++] = in[index2++];
        }
        for (int i = start; i <= end; i++) {
            in[i] = inTemp[i];
        }
    }

    public static void main(String[] args) {
        int[] in = {6,2,9,0,3,8,1,7,5,4};
        sort(in);
        System.out.println(in);
    }
}
