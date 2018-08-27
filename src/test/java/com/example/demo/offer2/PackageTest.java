package com.example.demo.offer2;

import org.junit.Test;

/**
 * @author DongDexuan
 * @date 2018-6-27 14:29
 * @desc
 */
public class PackageTest {

    @Test
    public void test() {
        int[] value = {80, 40, 30, 50};
        int[] weight = {4, 4, 6, 3};
        int i = package01(value, weight, 10);
        System.out.println(i);
        int i2 = package01_2(value, weight, 10);
        System.out.println(i2);
        int f = packageFull(value, weight, 10);
        System.out.println(f);
        int f2 = packageFull_2(value, weight, 10);
        System.out.println(f2);
    }

    /**0-1背包*/
    public int package01(int[] value, int[] weight, int capacity) {
        int size = value.length;
        int[][] dp = new int[size + 1][capacity + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= capacity; j++) {//正向循环
//            for (int j = capacity; j > 0; j--) {//反向循环也行
                if (get(weight, i) <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - get(weight, i)] + get(value, i));
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[size][capacity];
    }

    /**0-1背包*/
    public int package01_2(int[] value, int[] weight, int capacity) {
        int size = value.length;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = capacity; j > 0; j--) {//反向循环
                if (get(weight, i) <= j) {
                    dp[j] = Math.max(dp[j - 1], dp[j - get(weight, i)] + get(value, i));
                }
            }
        }
        return dp[capacity];
    }

    private int get(int[] k, int index) {
        return k[index - 1];
    }

    /**完全背包*/
    public int packageFull(int[] value, int[] weight, int capacity) {
        int size = value.length;
        int[][] dp = new int[size + 1][capacity + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= capacity; j++) {//正向循环
                if (get(weight, i) <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - get(weight, i)] + get(value, i));
                    System.out.println("i:" + i + " j:" + j + " value:" + dp[i][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[size][capacity];
    }

    /**完全背包*/
    public int packageFull_2(int[] value, int[] weight, int capacity) {
        int size = value.length;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j <= capacity; j++) {//正向循环
                if (get(weight, i) <= j) {
                    dp[j] = Math.max(dp[j], dp[j - get(weight, i)] + get(value, i));
                }
            }
        }
        return dp[capacity];
    }
}
