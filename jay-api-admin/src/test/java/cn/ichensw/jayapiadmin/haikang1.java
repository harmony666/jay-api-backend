package cn.ichensw.jayapiadmin;

import java.util.Scanner;



public class haikang1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入地图的大小和机器人的起点坐标：");
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.println("从起点到终点的不同路径数为：" + uniquePaths(m, n, x, y));
    }

    public static int uniquePaths(int m, int n, int x, int y) {
        int[][] dp = new int[m][n];

        // Initialize the dp array
        for(int i = x; i < m; i++) {
            dp[i][y] = 1;
        }
        for(int j = y; j < n; j++) {
            dp[x][j] = 1;
        }

        // Calculate the number of unique paths
        for(int i = x+1; i < m; i++) {
            for(int j = y+1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }
}

