package str;

/**
 * 正则表达式匹配:
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和'*' 的正则表达式匹配。
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的那一个元素。
 * <p>
 * https://github.com/MisterBooo/LeetCodeAnimation
 * </p>
 *
 * @author Ringo
 * @date 2021/6/3 14:17
 */
public class RegularExpress {

    public static void main(String[] args) {
    }

    // 1: 递归
    public static boolean isMatch1(String s, String p) {
        if (s.equals(p)) {
            return true;
        }

        // 判断字符串第一个字符是否匹配
        // 第一个字符不能是 '*'
        boolean isFirstMatch = false;
        if (!s.isEmpty() && !p.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            isFirstMatch = true;
        }

        if (p.length() >= 2 && p.charAt(1) == '*') {
            // 看 s[i,...n] 和 p[j+2,...m] 或者是 s[i+1,...n] 和 p[j,...m]
            return isMatch1(s, p.substring(2))
                    || (isFirstMatch && isMatch1(s.substring(1), p));
        }

        // 第一个字符匹配成功, 除去第一个字符，看后面的字符是否匹配
        // 看 s[i+1,...n] 和 p[j+1,...m]
        return isFirstMatch && isMatch1(s.substring(1), p.substring(1));
    }

    // 2: 动态规划
    public static boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }

        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();

        // dp[i][j] => is s[0, i - 1] match p[0, j - 1] ?
        boolean[][] dp = new boolean[sArr.length + 1][pArr.length + 1];

        dp[0][0] = true;

        for (int i = 1; i <= pArr.length; ++i) {
            dp[0][i] = pArr[i - 1] == '*' ? dp[0][i - 2] : false;
        }

        for (int i = 1; i <= sArr.length; ++i) {
            for (int j = 1; j <= pArr.length; ++j) {
                if (sArr[i - 1] == pArr[j - 1] || pArr[j - 1] == '.') {
                    // 看 s[0,...i-1] 和 p[0,...j-1]
                    dp[i][j] = dp[i - 1][j - 1];
                }

                if (pArr[j - 1] == '*') {
                    // 看 s[0,...i] 和 p[0,...j-2]
                    dp[i][j] |= dp[i][j - 2];

                    if (pArr[j - 2] == sArr[i - 1] || pArr[j - 2] == '.') {
                        // 看 s[0,...i-1] 和 p[0,...j]
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[sArr.length][pArr.length];
    }
}
