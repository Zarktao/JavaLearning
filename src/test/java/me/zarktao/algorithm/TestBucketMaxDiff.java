package me.zarktao.algorithm;

import me.zarktao.algorithm.exercise.BucketMaxDiff;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Tao on 2017/3/21.
 */
public class TestBucketMaxDiff {
    int[] input = {1, 7, 14, 9, 13, 14, 4};

    @Test
    public void testBucketMaxDiff() {
        assertTrue(BucketMaxDiff.findMaxDiff(input) == 4);
    }
}
