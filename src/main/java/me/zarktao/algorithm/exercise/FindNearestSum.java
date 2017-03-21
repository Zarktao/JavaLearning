package me.zarktao.algorithm.exercise;

import java.awt.*;
import java.util.Map;

/**
 * Created by Tao on 2017/3/21.
 * <p>
 * 给定一个整数sum,从有N个有序元素的数组中寻找元素a,b,使得a+b的结果最接近sum
 */
public class FindNearestSum {
    public static Point findNearestSum(int sum, int[] a) {
        int l = 0;
        int h = a.length - 1;
        int ls = -1;
        int hs = -1;
        int diff = Integer.MAX_VALUE;
        while (l < h) {
            int diffTmp = a[l] + a[h] - sum;
            if (Math.abs(diffTmp) < diff) {
                diff = diffTmp;
                ls = l;
                hs = h;
            }
            if (diffTmp > 0) h--;
            else if (diffTmp == 0) break;
            else l++;
        }
        return new Point(ls, hs);
    }
}
