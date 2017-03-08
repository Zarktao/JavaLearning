package me.zarktao.algorithm.sort;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Tao on 2017/3/7.
 */

public class CommonSort {
    public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
        int i;
        for (int p = 1; p < a.length; p++) {
            T tmp = a[p];
            for (i = p; i > 0 && tmp.compareTo(a[i - 1]) < 0; i--) {
                a[i] = a[i - 1];
            }
            a[i] = tmp;
        }
    }

    public static <T extends Comparable<? super T>> void shellSort(T[] a) {
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                for (int j = i - gap; j >= 0 && a[j].compareTo(a[j + gap]) > 0; j -= gap) {
                    T tmp = a[j + gap];
                    a[j + gap] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        T[] tmp = (T[]) new Comparable[a.length];
        mergeSort(a, tmp, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmp, int l, int r) {
        if (l < r) {
            int mid = (r + l) / 2;
            mergeSort(a, tmp, l, mid);
            mergeSort(a, tmp, mid + 1, r);
            merge(a, tmp, l, mid + 1, r);
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmp, int l, int r, int re) {
        int le = r - 1;
        int count = re + 1 - l;
        int ti = 0;
        while (l <= le && r <= re) {
            if (a[l].compareTo(a[r]) <= 0) {
                tmp[ti++] = a[l++];
            } else {
                tmp[ti++] = a[r++];
            }
        }
        while (l <= le)
            tmp[ti++] = a[l++];
        while (r <= re)
            tmp[ti++] = a[r++];
        //这里不使用l或r进行数组复制是因为在经过上面几个while循环中的操作之后，l和r已经不是原值
        //数组拷贝方法是native的，效率比正常复制要高
        System.arraycopy(tmp, 0, a, re - count + 1, count);
    }
}
