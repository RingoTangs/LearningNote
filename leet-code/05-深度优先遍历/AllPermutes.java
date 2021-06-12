package deep;

import java.util.ArrayList;
import java.util.List;

/**
 * leet-code(46) 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列。
 * 你可以 按任意顺序 返回答案。
 * nums 中的所有整数 互不相同。
 *
 * @author Ringo
 * @date 2021/6/11 15:50
 */
public class AllPermutes {

    private List<List<Integer>> ans = new ArrayList<>();

    private List<Integer> list = new ArrayList<>();

    /**
     * dfs 回溯算法
     *
     * @param nums    目标数组
     * @param visited 记录是否被访问
     */
    public void dfs(int[] nums, int[] visited) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            // 已经被访问过了, 直接 continue
            if (visited[i] == 1)
                continue;

            list.add(nums[i]);
            visited[i] = 1;

            dfs(nums, visited);

            list.remove(list.size() - 1);
            visited[i] = 0;
        }
    }

    // 递归算法
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null)
            return ans;
        int[] visited = new int[nums.length];
        dfs(nums, visited);
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new AllPermutes().permute(nums));
    }

}
