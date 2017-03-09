package me.zarktao.effective;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Tao on 2017/3/9.
 */
public class TestSingleton {
    @Test
    public void testCreate() {
        Singleton.Impl1.INSTANCE.method1();
    }
}
