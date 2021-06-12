package dp;

/**
 * leet-code(55) 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 *  
 *
 * @author Ringo
 * @date 2021/6/12 20:11
 */
public class CanJump {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(canJump(nums));
    }

    // 1: 动态规划
    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length < 1)
            return false;

        int len = nums.length;

        boolean[] dp = new boolean[len];
        dp[0] = true;

        for (int i = 1; i < len; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                if (dp[j] && nums[j] >= i - j) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len - 1];
    }

    // 2: 贪心算法
    public static boolean canJump1(int[] nums) {
        if (nums == null || nums.length < 1)
            return false;

        int len = nums.length;
        int rightMost = 0;

        for (int i = 0; i < len; ++i) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= len - 1)
                    return true;
            }
        }
        return false;
    }
}
