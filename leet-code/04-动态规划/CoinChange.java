package dp;

import java.util.Arrays;

/**
 * leet-code(322): 零钱兑换(爬楼梯进阶版)
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 *
 * <p>
 * 解析: https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
 * </p>
 *
 * @author Ringo
 * @date 2021/6/4 12:23
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        System.out.println(solution(coins, amount));
    }

    public static int solution(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Character.MAX_VALUE);          // 整个数组都用 MAX_VALUE 填充

        dp[0] = 0;

        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j < coins.length; ++j) {
                // 这里判断 ==> 防止数组下标越界
                if (i - coins[j] >= 0)
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }

        // 如果全是1元硬币, 硬币数最多等于 amount。
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
