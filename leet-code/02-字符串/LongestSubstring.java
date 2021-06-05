package str;

/**
 * leet-code(3) 无重复字符的最长子串:
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * https://github.com/MisterBooo/LeetCodeAnimation
 * </p>
 *
 * @author Ringo
 * @date 2021/6/2 20:11
 */
public class LongestSubstring {

    public static void main(String[] args) {
        String s = "abbbce";
        System.out.println(find(s));
    }

    // 滑动窗口
    public static int find(String s) {
        if (s == null)
            return 0;

        int[] freq = new int[256];      // 当前字符是否出现过
        int l = 0, r = -1;              // 滑动窗口

        // 注意: 要有一个记录的变量
        int res = 0;

        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                ++r;
                ++freq[s.charAt(r)];
            } else {
                --freq[s.charAt(l)];
                ++l;
            }

            // 求最大长度
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
