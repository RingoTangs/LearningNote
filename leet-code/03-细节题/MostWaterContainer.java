package details;

/**
 * leet-code(11) 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * <p>
 * 解析: https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode-solution/
 * </p>
 *
 * @author Ringo
 * @date 2021/6/5 20:34
 */
public class MostWaterContainer {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(solution(height));
        System.out.println(solutionDoublePointer(height));
    }

    // 解法一: 暴力破解（自己写的）leet-code 超时警告！
    public static int solution(int[] height) {
        if (height == null || height.length < 2)
            return 0;
        int len = height.length;
        int maxVolume = 0;

        // L: 距离（长方形的宽）
        for (int L = 1; L < len; ++L) {

            // 左边界
            for (int i = 0; i < len; ++i) {
                // 右边界
                int j = i + L;
                if (j >= len)
                    break;

                int h = Math.min(height[i], height[j]);     // 长方形的高
                maxVolume = Math.max(maxVolume, L * h);     // 计算长方形的体积
            }
        }
        return maxVolume;
    }

    // 解法二: 双指针（官方答案）
    public static int solutionDoublePointer(int[] height) {
        if (height == null || height.length < 2)
            return 0;
        int lo = 0, hi = height.length - 1;                     // 高、低位指针
        int maxVolume = 0;                                      // 最大容量

        while (lo < hi) {
            if (height[lo] < height[hi]) {
                maxVolume = Math.max(maxVolume, (hi - lo) * height[lo]);     // 长方形的高以低的一方为标准
                ++lo;
            } else {
                maxVolume = Math.max(maxVolume, (hi - lo) * height[hi]);
                --hi;
            }
        }
        return maxVolume;
    }
}
