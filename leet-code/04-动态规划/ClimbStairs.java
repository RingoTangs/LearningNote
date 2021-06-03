package dp;

/**
 * leet-code(70)题目:
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * <p>
 * 解析 https://www.cnblogs.com/hengzhezou/p/11042151.html
 * </p>
 *
 * @author Ringo
 * @date 2021/6/3 19:47
 */
public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(solution1(10));
        System.out.println(solution2(10));
    }

    // 1: 递归, 但是leetCode 提交会超时
    public static int solution1(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        return solution1(n - 1) + solution1(n - 2);
    }

    // 2: 动态规划
    public static int solution2(int n) {
        int[] stairs = new int[n + 3];
        stairs[1] = 1;
        stairs[2] = 2;
        for (int i = 3; i <= n; i++) {
            stairs[i] = stairs[i - 1] + stairs[i - 2];
        }
        return stairs[n];
    }
}
