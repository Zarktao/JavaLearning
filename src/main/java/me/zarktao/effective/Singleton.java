package me.zarktao.effective;

/**
 * Created by Tao on 2017/3/9.
 */
public class Singleton {
    /**
     * Effective Java中提倡的单例实现方式
     */
    public enum Impl1 {
        INSTANCE;

        public void method1() {
            System.out.println("使用单元素枚举实现单例");
        }
    }

    /**
     * 双重检查锁定
     * 工厂方式实现，延迟加载
     * 缺点：不可继承，效率低
     */
    public static class Impl2 {
        private static Impl2 instance = null;

        private Impl2() {
        }

        public static Impl2 getInstance() {
            if (instance == null) {
                synchronized (Impl2.class) {
                    if (instance == null) {
                        instance = new Impl2();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * 网上评价较好的内部类实现
     *
     * @link http://blog.csdn.net/yongaini10/article/details/52738866
     * JVM内部的机制能够保证当一个类被加载的时候，这个类的加载过程是线程互斥的。
     * 这样当我们第一次调用getInstance的时候，JVM能够帮我们保证instance只被创建一次，
     * 并且会保证把赋值给instance的内存初始化完毕，这样我们就不用担心上面的问题(PS:2)。
     * 此外该方法也只会在第一次调用的时候使用互斥机制，这样就解决了低效问题(PS:1)。
     * 最后instance是在第一次加载SingletonContainer类时被创建的，
     * 而SingletonContainer类则在调用getInstance方法的时候才会被加载，因此也实现了惰性加载。
     */
    //TODO: why? ClassLoader机制
    public static class Impl3 {
        private Impl3() {
        }

        private static class InstanceHolder {
            private static Impl3 instance = new Impl3();
        }

        public static Impl3 getInstance() {
            return InstanceHolder.instance;
        }
    }

}
