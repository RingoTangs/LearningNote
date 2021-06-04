package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leet-code(120) 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 * <p>
 * 解析: https://www.cnblogs.com/hengzhezou/p/11044478.html
 * </p>
 *
 * @author Ringo
 * @date 2021/6/4 13:02
 */
public class MinPathSumTriangle {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3, 4)));
        triangle.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
        triangle.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));

        System.out.println(solution(triangle));
    }

    /**
     * 2
     * 3 4
     * 6 5 7
     * 4 1 8 3
     * 思路: 从下向上迭代
     */
    public static int solution(List<List<Integer>> triangle) {
        int level = triangle.size();
        int[] dp = new int[level + 1];

        for (int i = level - 1; i >= 0; --i) {
            List<Integer> cur = triangle.get(i);
            for (int j = 0; j < cur.size(); ++j) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + cur.get(j);
            }
        }
        return dp[0];
    }
}
