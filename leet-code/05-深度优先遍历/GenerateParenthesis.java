package deep;

import java.util.ArrayList;
import java.util.List;

/**
 * leet-code(22) 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * @author Ringo
 * @date 2021/6/8 15:13
 */
public class GenerateParenthesis {

    private List<String> ans = new ArrayList<>();

    private StringBuilder sb = new StringBuilder();

    public List<String> solution(int n) {
        dfs(n, 0, 0);
        return ans;
    }

    /**
     * @param max   括号总对数
     * @param open  左括号数
     * @param close 右括号数
     */
    public void dfs(int max, int open, int close) {
        if (open == max && close == max) {
            ans.add(sb.toString());
            return;
        }
        if (open == close) {
            sb.append("(");
            dfs(max, open + 1, close);
            sb.deleteCharAt(sb.length() - 1);
        } else if (open > close) {
            if (open < max) {
                sb.append("(");
                dfs(max, open + 1, close);
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
            dfs(max, open, close + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new GenerateParenthesis().solution(3));
    }

}
