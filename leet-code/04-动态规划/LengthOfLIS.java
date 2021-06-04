package dp;

/**
 * leet-code(300) 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 解析 https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
 * </p>
 *
 * @author Ringo
 * @date 2021/6/4 14:27
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        int[] nums = {4, 10, 4, 3, 8, 9};
        System.out.println(solution(nums));
    }

    public static int solution(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] dp = new int[nums.length];

        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = 1;                                      // 初始化, 每个dp初始化都是1
            for (int j = 0; j < i; ++j) {                   // 遍历 i 前面的数
                if (nums[i] > nums[j])                      // nums[i] > nums[j]证明: 这里有上升子序列
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
