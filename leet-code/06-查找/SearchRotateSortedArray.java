package search;

/**
 * leet-code(33): 搜索旋转排序数组
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * @author Ringo
 * @date 2021/6/9 15:12
 */
public class SearchRotateSortedArray {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(nums, 2));
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length < 1)
            return -1;

        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) >> 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < nums[hi]) {                    // 检查 mid 的位置: mid 所在位置只有两种情况
                if (nums[mid] < target && target < nums[hi])
                    lo = mid + 1;
                else if (nums[lo] == target)                    // 不在右侧递增的范围内, 需要检查端点
                    return lo;
                else if (nums[hi] == target)
                    return hi;
                else
                    hi = mid - 1;
            } else {
                if (nums[lo] < target && target < nums[mid])
                    hi = mid - 1;
                else if (nums[lo] == target)                    // 不在左侧递增范围内, 需要检查两侧端点
                    return lo;
                else if (nums[hi] == target)
                    return hi;
                else
                    lo = mid + 1;
            }
        }
        return -1;
    }
}
