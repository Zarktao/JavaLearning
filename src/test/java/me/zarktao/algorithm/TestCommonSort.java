package me.zarktao.algorithm;

import me.zarktao.algorithm.sort.CommonSort;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by Tao on 2017/3/7.
 */
public class TestCommonSort {
    Integer[] inputA = new Integer[]{34, 8, 64, 51, 32, 21};
    Integer[] outputA = new Integer[]{8, 21, 32, 34, 51, 64};


    @Test
    public void testInsertionSort() {
        Integer[] test = inputA.clone();
        CommonSort.insertionSort(test);
        assertTrue("InsertionSort inputA test failed.", Arrays.deepEquals(test, outputA));
    }
}
