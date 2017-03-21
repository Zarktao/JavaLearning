package me.zarktao.algorithm;

import me.zarktao.algorithm.exercise.FindMaxIntervalSum;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Tao on 2017/3/21.
 */
public class TestFindMaxIntervalSum {
    int[] inputA = {3, 9, 7, 5, 1, 3, 1, 2, 7};

    @Test
    public void testFindMaxSum() {
        assertTrue(FindMaxIntervalSum.findMaxSum(inputA) == 24);
    }
}
