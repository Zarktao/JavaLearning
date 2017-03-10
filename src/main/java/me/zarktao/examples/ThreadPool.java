package me.zarktao.examples;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Tao on 2017/3/9.
 */
public class ThreadPool {
    public static void cachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }
    public static void fixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(0);
    }
    public static void scheduledThreadPool() {
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(0);
    }
    public static void singleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    }
}
