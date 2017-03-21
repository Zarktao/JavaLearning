package me.zarktao.algorithm.exercise;

/**
 * Created by Tao on 2017/3/21.
 * <p>
 * N个未排序的整数，在线性时间内，求这N个整数在数轴上相邻两个数之间的最大差值
 */
public class BucketMaxDiff {


    public static int findMaxDiff(int[] a) {
        int min = a[0];
        int max = a[0];

        //桶初始化
        Bucket[] buckets = new Bucket[a.length];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }

        //找最值
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) max = a[i];
            else if (a[i] < min) min = a[i];
        }

        //将数据放入桶中
        int totalDiff = max - min;
        int maxGap = totalDiff / a.length;
        int bucketPoint;
        for (int i = 0; i < a.length; i++) {
            bucketPoint = (a[i] - min) * a.length / totalDiff;
            if (bucketPoint > a.length - 1) bucketPoint = a.length - 1;
            buckets[bucketPoint].add(a[i]);
        }

        //计算桶间隔
        for (int i = 0, j = 1; j < buckets.length; j++) {
            if (buckets[j].isValid) {
                if (buckets[j].min - buckets[i].max > maxGap)
                    maxGap = buckets[j].min - buckets[i].max;
                i = j;
            }
        }

        /*System.out.println(totalDiff);
        System.out.println(maxGap);
        for (int i = 0; i < a.length; i++) {
            System.out.println("bucket_" + i + "_" + buckets[i].isValid + "_" + buckets[i].min + "_" + buckets[i].max);
        }*/

        return maxGap;
    }

    public static class Bucket {
        boolean isValid = false;
        int max;
        int min;

        void add(int a) {
            if (!isValid) {
                max = a;
                min = a;
                isValid = true;
            } else {
                if (a > max) max = a;
                else if (a < min) min = a;
            }
        }
    }
}
