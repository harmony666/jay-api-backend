package cn.ichensw.jayapiadmin;

import java.util.*;

class TreeNode {
    int val;
    List<TreeNode> children;

    public TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}

public class lianxiang {
    public static TreeNode buildTree(int n, List<Integer> edges) {
        List<TreeNode> nodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nodes.add(new TreeNode(i));
        }
        for (int i = 0; i < edges.size(); i++) {
            nodes.get(edges.get(i) - 1).children.add(nodes.get(i + 1));
        }
        return nodes.get(0);
    }

    public static int dfs(TreeNode node, Map<Integer, Integer> depthMap) {
        if (node.children.isEmpty()) {
            depthMap.put(node.val, 1);
            return 1;
        }

        for (TreeNode child : node.children) {
            depthMap.put(node.val, depthMap.getOrDefault(node.val, 0) + dfs(child, depthMap));
        }

        return depthMap.get(node.val);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int Q = scanner.nextInt();
        scanner.nextLine();

        List<Integer> edges = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            edges.add(scanner.nextInt());
        }
        scanner.nextLine();

        List<Integer> queries_a = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            queries_a.add(scanner.nextInt());
        }
        scanner.nextLine();

        List<Integer> queries_b = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            queries_b.add(scanner.nextInt());
        }

        TreeNode root = buildTree(n, edges);
        Map<Integer, Integer> depthMap = new HashMap<>();
        dfs(root, depthMap);

        int xorSumResult = 0;
        for (int i = 0; i < Q; i++) {
            xorSumResult ^= depthMap.get(queries_a.get(i)) * depthMap.get(queries_b.get(i));
        }

        System.out.println(xorSumResult);
    }
}
