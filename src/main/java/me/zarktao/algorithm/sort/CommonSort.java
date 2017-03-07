package me.zarktao.algorithm.sort;

import java.util.Arrays;

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
}
