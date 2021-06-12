package details;

/**
 * leet-code(42) 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 解析: https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode-solution-tuvc/
 * </p>
 *
 * @author Ringo
 * @date 2021/6/11 13:25
 */
public class Trap {

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution(height));
    }

    // 1: 辅助数组(填平算法)
    public static int solution(int[] height) {
        if (height == null || height.length < 1)
            return 0;
        int n = height.length;

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i)
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i)
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);

        int ans = 0;
        for (int i = 0; i < n; ++i)
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];

        return ans;
    }
}
