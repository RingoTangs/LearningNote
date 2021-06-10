package deep;

import java.util.ArrayList;
import java.util.List;

/**
 * leet-code(39)
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * @author Ringo
 * @date 2021/6/10 12:31
 */
public class CombinationSum {

    private List<List<Integer>> ans = new ArrayList<>();

    private List<Integer> list = new ArrayList<>();

    // 递归算法
    public void dfs(int[] candidates, int start, int target) {
        if (target < 0)
            return;
        if (target == 0) {
            // 注意: Java中存的都是另一个对象的引用
            // 由于下面要remove(), 我们需要new ArrayList<>(list) 来保存结果。否则结果会被清除。
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; ++i) {
            list.add(candidates[i]);
            dfs(candidates, i, target - candidates[i]);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null)
            return ans;
        dfs(candidates, 0, target);
        return ans;
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        System.out.println(new CombinationSum().combinationSum(candidates, 7));
    }
}
