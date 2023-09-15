package cn.ichensw.jayapiadmin;

import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 木棍数量
        int k = scanner.nextInt(); // 最多移动操作次数
        int[] a = new int[n]; // 初始算珠数量

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // 移动算珠以获得最小字典序数组
        for (int i = 0; i < n - 1; i++) {
            int move = Math.min(k, a[i]); // 可以移动的算珠数量
            a[i] -= move; // 移动算珠
            a[n - 1] += move; // 移动的算珠放到最后一个木棍上
            k -= move; // 更新剩余移动次数
        }

        // 输出结果
        for (int i = 0; i < n; i++) {
            System.out.print(a[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}

