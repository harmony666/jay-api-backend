package cn.ichensw.jayapiadmin;

import java.util.*;
public class test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();
        String text = scanner.nextLine();

        // 预处理所有长度不超过10的子串
        Map<String, Integer> counts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= Math.min(i + 10, n); j++) {
                String sub = text.substring(i, j);
                counts.put(sub, counts.getOrDefault(sub, 0) + 1);
            }
        }

        // 查询
        for (int i = 0; i < q; i++) {
            String query = scanner.nextLine();
            System.out.println(counts.getOrDefault(query, 0));
        }
    }
}
