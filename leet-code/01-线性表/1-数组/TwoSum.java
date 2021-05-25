package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 图解算法地址: https://github.com/MisterBooo/LeetCodeAnimation
 * 第1题
 *
 * @author Ringo
 * @date 2021/5/25 15:14
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15, 15};
        System.out.println(twoSum(arr, 30));
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * @param arr    数组
     * @param target 目标值
     * @return 数组下标。不存在返回 "[]"
     */
    public static String twoSum(int[] arr, int target) {
        if (arr == null || arr.length == 0)
            return "[]";

        // key: 值; value: 下标
        Map<Integer, Integer> record = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int remain = target - arr[i];
            Integer index = record.get(remain);
            if (index != null) {
                return Arrays.toString(new int[]{index, i});
            }

            // HashMap中不存在就存入即可
            record.put(arr[i], i);
        }
        return "[]";
    }
}
