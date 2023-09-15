package cn.ichensw.jayapiadmin;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i * 4; // 初始化为每块巧克力板只有一单位面积的情况
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + j * 4); // 动态规划，更新最小周长
            }
        }
        System.out.println(dp[n]);
    }
}