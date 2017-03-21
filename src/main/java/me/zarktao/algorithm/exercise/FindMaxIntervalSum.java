package me.zarktao.algorithm.exercise;

/**
 * Created by Tao on 2017/3/21.
 * <p>
 * 给定一个数组，可以从数组中取出下标不连续的任意个数，求可以取出的数的和的最大值.
 * 例如：给出数组A[]={1,2,2,5,3,4,3}可以取出的最大和为2+5+4=11。
 * 现再给定数组{3,9,7,5,1,3,1,2,7}，能取出的数的和的最大值是24。
 */
public class FindMaxIntervalSum {
    public static int findMaxSum(int[] a) {
        if (a.length == 0) {
            return 0;
        } else if (a.length == 1) {
            return a[0];
        } else if (a.length == 2) {
            return Integer.max(a[0], a[1]);
        }
        int[] b = new int[a.length];
        b[0] = a[0];
        b[1] = a[1];

        for (int i = 2; i < a.length; i++) {//从第3个位置开始依次计算前i个位置的最大不连续和
            b[i] = Integer.MIN_VALUE;
            for (int j = 0; j <= i - 2; j++) {//从0到i-2的最大不连续和依次和当前位置相加，最大的赋给当前位置
                if (b[j] + a[i] > b[i])
                    b[i] = b[j] + a[i];
            }
        }

        return b[a.length - 1];
    }
}
