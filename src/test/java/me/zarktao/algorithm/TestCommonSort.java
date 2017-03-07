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
    Integer[] inputB = new Integer[]{1391376, 463792, 198768, 86961, 33936, 13776, 4592, 1968, 861, 336, 112, 48, 21, 7, 3, 1};
    Integer[] outputB = new Integer[]{1, 3, 7, 21, 48, 112, 336, 861, 1968, 4592, 13776, 33936, 86961, 198768, 463792, 1391376};


    @Test
    public void testInsertionSort() {
        Integer[] test = inputA.clone();
        CommonSort.insertionSort(test);
        assertTrue("InsertionSort inputA test failed.", Arrays.deepEquals(test, outputA));
        test = inputB.clone();
        CommonSort.insertionSort(test);
        assertTrue("InsertionSort inputB test failed.", Arrays.deepEquals(test, outputB));
    }

    @Test
    public void testShellSort() {
        Integer[] test = inputA.clone();
        CommonSort.shellSort(test);
        assertTrue("ShellSort inputA test failed.", Arrays.deepEquals(test, outputA));
        test = inputB.clone();
        CommonSort.shellSort(test);
        assertTrue("ShellSort inputB test failed.", Arrays.deepEquals(test, outputB));
    }
}
