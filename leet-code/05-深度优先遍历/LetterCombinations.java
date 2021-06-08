package deep;

import java.util.ArrayList;
import java.util.List;

/**
 * leet-code(17) 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * @author Ringo
 * @date 2021/6/6 12:57
 */
public class LetterCombinations {

    // number 到 letter 的映射
    private String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    // 路径
    private StringBuilder sb = new StringBuilder();

    // 结果集
    private List<String> res = new ArrayList<>();

    public List<String> solution(String digits) {
        if (digits == null || digits.length() < 1)
            return res;
        backtrace(digits, 0);
        return res;
    }

    /**
     * 回溯函数
     *
     * @param digits 号码字符串
     * @param index  每个号码的下标
     */
    public void backtrace(String digits, int index) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String val = map[digits.charAt(index) - '2'];   // digits.charAt(index) is '3' ==> '3'-'2'=1
        for (char ch : val.toCharArray()) {
            sb.append(ch);
            backtrace(digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);           // 删除当前操作, 保证一轮回溯下来, StringBuilder 还是初始状态
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        System.out.println(new LetterCombinations().solution(digits));
    }
}
