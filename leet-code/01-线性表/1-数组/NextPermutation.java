package array;

import java.util.Arrays;

/**
 * leet-code(31) 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * @author Ringo
 * @date 2021/6/8 17:25
 */
public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = {3, 9, 2, 6};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                // nums[i...len-1] 递减的, 排完序就变成递增了
                Arrays.sort(nums, i, len);
                for (int j = i; j < len; j++) {
                    // 在递增序列中找到第一个比nums[i-1]大的数并交换
                    if (nums[j] > nums[i - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        return;
                    }
                }
            }
        }

        // {3, 2, 1} 这种情况直接排序即可!
        Arrays.sort(nums);
        return;
    }
}
