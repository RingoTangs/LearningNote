package dp;

/**
 * leet-code(5): 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 解析: https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
 * </p>
 *
 * @author Ringo
 * @date 2021/6/5 14:00
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(solution(s));
    }

    /**
     * P(i, j) 代表 s[i...j] 为回文。
     * P(i, j) <==> P(i+1, j-1) && Si = Sj。
     *
     * @param s 字符串
     * @return 最长回文子串
     */
    public static String solution(String s) {
        if (s == null || s.length() < 2)
            return s;

        int len = s.length();

        int begin = 0;
        int maxLen = 1;

        // dp[i][j] 表示 s[i...j] 是否为回文串
        boolean[][] dp = new boolean[len][len];

        // 初始化, 所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; ++i) {
            dp[i][i] = true;
        }

        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; ++L) {

            // 设置左边界
            for (int i = 0; i < len; ++i) {

                // 设置右边界: 从 L = j - i + 1 转化而来
                int j = i + L - 1;

                if (j >= len)
                    break;

                if (s.charAt(i) != s.charAt(j))
                    dp[i][j] = false;
                else {
                    // 注意: a, aa, aba 这三种情况都是回文串
                    if (j - i < 3)
                        dp[i][j] = true;
                    else
                        dp[i][j] = dp[i + 1][j - 1];
                }

                // 如果 s[i..j] 是回文子串, 再看 i..j 之间的长度是否最大
                if (dp[i][j] == true && L > maxLen) {
                    begin = i;
                    maxLen = L;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
