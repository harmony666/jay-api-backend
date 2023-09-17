package cn.ichensw.jayapiadmin;


import java.util.Scanner;

public class test4 {
    static final int MOD = 1000000007;
    static int[][][][] dp;
    static String s, t;
    static String[][] sub_s, sub_t;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        t = scanner.nextLine();
        int k = scanner.nextInt();
        int n = s.length(), m = t.length();
        dp = new int[n + 1][n + 1][m + 1][k + 1];
        sub_s = new String[n + 1][n + 1];
        sub_t = new String[m + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                sub_s[i][j] = s.substring(i, j);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j <= m; j++) {
                sub_t[i][j] = t.substring(i, j);
            }
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int l = 0; l <= m; l++) {
                    for (int o = 0; o <= k; o++) {
                        dp[i][j][l][o] = -1;
                    }
                }
            }
        }
        System.out.println(solve(0, n, m, k));
    }

    static int solve(int start, int end, int m, int k) {
        if (k == 0) {
            if (sub_s[start][end].equals(sub_t[0][m])) {
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[start][end][m][k] != -1) {
            return dp[start][end][m][k];
        }
        int res = 0;
        for (int i = start + 1; i < end; i++) {
            for (int j = 0; j <= m; j++) {
                for (int l = j; l <= m; l++) {
                    if (sub_s[i][end].equals(sub_t[j][l])) {
                        res += solve(start, i, m - (l - j), k - 1);
                        if (res >= MOD) {
                            res -= MOD;
                        }
                    }
                }
            }
        }
        return dp[start][end][m][k] = res;
    }
}
