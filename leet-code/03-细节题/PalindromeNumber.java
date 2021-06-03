package details;

/**
 * 题目:
 * 判断一个整数是否是回文数。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * https://github.com/MisterBooo/LeetCodeAnimation
 * </p>
 *
 * @author Ringo
 * @date 2021/6/3 12:55
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println("===================== Integer2StringJudge =====================");
        int x1 = 1221;
        System.out.println(Integer2StringJudge(x1));

        System.out.println("===================== cleverSolution =====================");
        System.out.println(cleverSolution(x1));
    }

    // 1: 整数 ==> 字符串
    public static boolean Integer2StringJudge(int x) {
        String reversedStr = new StringBuilder(x + "").reverse().toString();
        return (x + "").equals(reversedStr);
    }

    // 2: 巧妙解法
    public static boolean cleverSolution(int x) {

        // 负数、1230 这种末尾是0的肯定不是回文数
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        return x == revertedNumber || x == revertedNumber / 10;
    }
}
