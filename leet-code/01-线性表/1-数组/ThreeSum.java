package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leet-code(15) 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 解析 https://leetcode-cn.com/problems/3sum/solution/san-shu-zhi-he-by-leetcode-solution/
 * </p>
 *
 * @author Ringo
 * @date 2021/6/6 10:49
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(solution(nums));
    }

    /**
     * 解法: 排序 + 双指针
     * step1: 排序
     * step2: a + b + c = 0 ==> b + c = -a ==> target = -a
     * step3: 因为要去重, 采用双指针。b 表示 lo, c 表示 hi
     *
     * <p>
     * Question: TwoSum(leet-code(1)) 能用双指针吗?
     * Answer: TwoSum 要求返回的是数组下标, ThreeSum 要求返回数组具体的值。
     * 返回 index 显然不能用到排序, 双指针的前提是有序数组。
     * </p>
     */
    public static List<List<Integer>> solution(int[] nums) {

        if (nums == null || nums.length < 2)
            return new ArrayList<>();

        Arrays.sort(nums);                                          // 排序

        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;

        for (int i = 0; i < len - 2; ++i) {
            // nums[0] > 0 后面的都大于 0, 相加不可能是 0
            if (nums[i] > 0) break;

            // 去重, nums[i-1] == nums[i], 前面的验证过了, 后面相等的无需验证
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int target = -nums[i];                                  // b + c = -a
            int lo = i + 1, hi = len - 1;                           // 左、右指针

            while (lo < hi) {
                if (nums[lo] + nums[hi] == target) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[lo], nums[hi])));
                    ++lo;
                    --hi;

                    // 左右指针在变化也要去重
                    while (lo < hi && nums[lo] == nums[lo - 1]) ++lo;
                    while (lo < hi && nums[hi] == nums[hi + 1]) --hi;
                } else if (nums[lo] + nums[hi] < target)
                    ++lo;
                else
                    --hi;
            }
        }
        return ans;
    }
}
