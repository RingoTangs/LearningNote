package dp;

/**
 * leet-code(53) 最大子序和:
 * 给定一个整数数组 nums, 找到一个具有最大和的连续子数组（子数组最少包含一个元素）, 返回其最大和。
 * <p>
 * 解析 https://www.cnblogs.com/hengzhezou/p/11042789.html
 * </p>
 *
 * @author Ringo
 * @date 2021/6/3 21:14
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution(nums));

        int[] nums1 = {-1};
        System.out.println(solution(nums1));
    }


    public static int solution(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; ++i) {
            // 这里是重点
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > max)
                max = dp[i];
        }
        return max;
    }
}
