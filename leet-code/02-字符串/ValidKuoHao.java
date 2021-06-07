package str;

import java.util.Stack;

/**
 * leet-code(20): 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 1. 左括号必须用相同类型的右括号闭合。
 * 2. 左括号必须以正确的顺序闭合。
 *
 * @author Ringo
 * @date 2021/6/7 18:30
 */
public class ValidKuoHao {

    public static boolean isValid(String s) {
        if (s == null || s.length() < 2)
            return false;

        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') stack.push(')');
            else if (ch == '[') stack.push(']');
            else if (ch == '{') stack.push('}');
            else if ( ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty() || ch != stack.pop())
                    return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "([])";
        System.out.println(isValid(s));
    }
}
