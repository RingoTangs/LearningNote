package array;

/**
 * 有序数组按照某个元素旋转后的搜索
 * · 详解二分查找: https://www.cnblogs.com/kyoner/p/11080078.html
 *
 * @author Ringo
 * @date 2021/5/25 11:42
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println("======================== search1 ========================");
        int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(search1(arr1, 2));
    }

    /**
     * 2.1.3. 假设在你不知道的情况下, 有序数组按照某个轴心旋转。
     * i.e., 0 1 2 3 4 5 6 7 可能变成 4 5 6 7 0 1 2。
     * 在该数组中没有重复的元素, 去搜索目标值。(两组有序数组, 拼接在一起查找)
     *
     * @param arr    目标数组
     * @param target 目标元素
     * @return 目标元素的下标。查不到返回-1。
     */
    public static int search1(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;

        int first = 0, last = arr.length;

        while (first < last) {
            int mid = first + (last - first) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[first] <= arr[mid]) {                        // 左边是单调递增的
                if (arr[first] <= target && target < arr[mid])
                    last = mid;
                else
                    first = mid + 1;
            } else {                                             // 右边是单调递增的
                if (arr[mid] < target && target <= arr[last - 1])
                    first = mid + 1;
                else
                    last = mid;
            }
        }
        return -1;
    }
}
