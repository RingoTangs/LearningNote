package search;

import java.util.Arrays;

/**
 * leet-code(34) 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * @author Ringo
 * @date 2021/6/10 11:38
 */
public class SearchRange {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(null, 6)));
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null)
            return new int[]{-1, -1};

        int[] res = new int[2];
        res[0] = search(nums, target, true);
        res[1] = search(nums, target, false);
        return res;
    }

    /**
     * 搜索左端点/右端点
     *
     * @param nums        目标数组
     * @param target      目标值
     * @param leftOrRight true: 搜索左端点; false: 搜索右端点
     * @return
     */
    public static int search(int[] nums, int target, boolean leftOrRight) {
        int lo = 0, hi = nums.length - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (target < nums[mid])
                hi = mid - 1;
            else if (target > nums[mid])
                lo = mid + 1;
            else {
                // target == nums[mid], 将根据情况移动指针
                ans = mid;
                if (leftOrRight)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
        }
        return ans;
    }
}
