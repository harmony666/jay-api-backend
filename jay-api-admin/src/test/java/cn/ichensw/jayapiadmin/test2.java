package cn.ichensw.jayapiadmin;

import java.util.*;

public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextLong();
        }
        System.out.println(maxZeros(a, b, n));
    }

    public static int maxZeros(long[] a, long[] b, int n) {
        if (n == 1) {
            return b[0] == 0 ? 1 : 0;
        }

        List<Double> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i] == 0 && b[i] == 0) {
                continue;
            }
            if (a[i] == 0) {
                continue;
            }
            double mul = -(double)b[i] / a[i];
            list.add(mul);
        }

        Collections.sort(list);

        int maxCount = 1, count = 1;
        for (int i = 1; i < list.size(); i++) {
            if (Math.abs(list.get(i) - list.get(i - 1)) < 1e-9) {
                count++;
            } else {
                count = 1;
            }
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }
}
