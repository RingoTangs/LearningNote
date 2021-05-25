package array;

import java.util.Arrays;

/**
 * @author Ringo
 * @date 2021/5/24 21:08
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 3, 3, 4, 6, 8, 8, 9};
        System.out.println("================ removeDuplicates ================");
        int newLen1 = removeDuplicates(arr1);                 // 1, 3, 4, 6, 8, 9, 8, 8, 9
        System.out.println(newLen1);
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr1, 0, newLen1)));

        int[] arr2 = {1, 1, 1, 2, 2, 2, 3, 3, 4, 5};
        System.out.println("================ removeDuplicatesAllowedMostTwice ================");
        int newLen2 = removeDuplicatesAllowedMostTwice(arr2);
        System.out.println(newLen2);
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr2, 0, newLen2)));


        int[] arr3 = {1, 2, 3, 4, 4, 5, 5, 6, 7, 7, 8, 8, 8, 9};
        System.out.println("================ removeDuplicatesAllowedMostN ================");
        int newLen3 = removeDuplicatesAllowedMostN(arr3, 3);
        System.out.println(newLen3);
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr3, 0, newLen3)));
    }

    // 2.1.1 删除有序数组中的重复元素, 并返回新的长度。
    public static int removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int index = 0;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[index] != arr[i])
                arr[++index] = arr[i];
        }
        return index + 1;
    }

    // 2.1.2 删除有序数组中的重复元素, 要求最多允许重复两次
    public static int removeDuplicatesAllowedMostTwice(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length <= 2) return arr.length;

        int index = 2;
        for (int i = 2; i < arr.length; ++i) {
            if (arr[i] != arr[index - 2])
                arr[index++] = arr[i];
        }
        return index;
    }


    /**
     * 删除有序数组中的重复元素, 要求最多允许重复 N 次
     *
     * @param arr        数组
     * @param occurTimes 允许元素最大重复的次数
     * @return 新数组的长度
     */
    public static int removeDuplicatesAllowedMostN(int[] arr, int occurTimes) {
        if (occurTimes < 1) return arr.length;
        if (arr == null || arr.length == 0) return 0;
        if (arr.length <= occurTimes) return arr.length;

        int index = occurTimes;
        for (int i = occurTimes; i < arr.length; ++i) {
            if (arr[i] != arr[index - occurTimes])
                arr[index++] = arr[i];
        }
        return index;
    }
}
