package me.zarktao.algorithm;

import me.zarktao.algorithm.exercise.FindNearestSum;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by Tao on 2017/3/21.
 */
public class TestFindNearestSum {
    int[] inputA = new int[]{8, 21, 32, 34, 51, 64};
    int[] inputB = new int[]{1, 3, 7, 21, 48, 112, 336, 861, 1968, 4592, 13776, 33936, 86961, 198768, 463792, 1391376};

    @Test
    public void testFind() {
        Point outputA = FindNearestSum.findNearestSum(53, inputA);
        assertTrue(outputA.getX() == 1);
        assertTrue(outputA.getY() == 2);
        Point outputB = FindNearestSum.findNearestSum(5, inputB);
        assertTrue(outputB.getX() == 0);
        assertTrue(outputB.getY() == 1);
    }
}
