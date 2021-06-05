package dp;

/**
 * leet-code(64) 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 解析: https://leetcode-cn.com/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-by-leetcode-solution/
 * </p>
 *
 * @author Ringo
 * @date 2021/6/4 19:12
 */
public class MinPathSumGrid {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(solution(grid));

    }

    public static int solution(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row][col];

        // 1: 初始化
        dp[0][0] = grid[0][0];
        for (int i = 1; i < col; ++i) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < row; ++i) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // 2: 动态规划
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }

        return dp[row - 1][col - 1];
    }
}
