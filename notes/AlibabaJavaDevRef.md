# 阿里巴巴Java开发手册中的几点注意

## 所有POJO类必须使用包装数据类型，且不赋初值
**原因** POJO类型没有初值是提醒使用者需要在使用时，必须自己显示的进行赋值，任何NPE问题或者入库检查须由使用者来保证。例如：数据库查询的结果可能是null，因为自动拆箱，所以用基本数据类型有NPE风险；数据不存在是需要正确显示`-`而不是`0`

## 构造方法里不要加入业务逻辑

## ArrayList的subList
* ArrayList的subList结果不可强转成ArrayList，否则会抛出ClassCastException异常：`java.util.RandomAccessSubList cannot be cast to java.util.ArrayList ;` **说明**：subList 返回的是 ArrayList 的内部类 SubList，并不是 ArrayList ，而是 ArrayList 的一个视图，对于SubList子列表的所有操作最终会反映到原列表上。 
* 在subList场景中，高度注意对原集合元素个数的修改，会导致子列表的遍历、增加、删除均产生ConcurrentModificationException 异常。

## Arrays.asList()
使用工具类 Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的add/remove/clear方法会抛出 UnsupportedOperationException异常。 

## 集合初始化时，指定集合初始值大小。 
* **说明**：HashMap使用 HashMap(int initialCapacity) 初始化， 
* **正例**：initialCapacity =  (需要存储的元素个数 / 负载因子) + 1。 注意负载因子 （即loaderfactor）默认为0.75，如果暂时无法确定初始值大小，请设置为 16。 
* **反例**：HashMap需要放置 1024个元素，由于没有设置容量初始大小，随着元素不断增加，容量7 次被迫扩大，resize需要重建hash表，严重影响性能。

## 使用entrySet遍历Map类集合KV，而不是 keySet方式进行遍历。 
* **说明**：keySet其实是遍历了 2次，一次是转为 Iterator对象，另一次是从 hashMap 中取出 key 所对应的value。而 entrySet只是遍历了一次就把 key和value都放到了entry中，效率更高。如果是JDK8，使用Map.foreach方法。 
* **正例**：values()返回的是 V值集合，是一个list集合对象；keySet()返回的是K值集合，是一个Set集合对象；entrySet()返回的是K-V值组合集合。 

## Map类集合K/V能不能存储 null值的情况
| 集合类    | key          | value        | super      | 说明     |
| --------- | ------------ | ------------ | ---------- | -------- |
| Hashtable | 不允许为null | 不允许为null | Dictionary | 线程安全 |
| ConcurrentHashMap | 不允许为null | 不允许为null | AbstractMap | 分段锁技术 |
| TreeMap | 不允许为null | 允许为null | AbstractMap | 线程不安全 |
| HashMap | 允许为null | 允许为null | AbstractMap | 线程不安全 |

## 线程池
线程池不允许使用 Executors去创建，而是通过 ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 

## 避免Random实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一 seed 导致的性能下降。 
* **说明**：Random实例包括 java.util.Random 的实例或者 Math.random()的方式。 
* **正例**：在JDK7之后，可以直接使用 API  ThreadLocalRandom，而在 JDK7 之前，需要编码保证每个线程持有一个实例。

## 随机数
注意 Math.random() 这个方法返回是 double类型，注意取值的范围 0≤x<1（能够取到零值，注意除零异常），如果想获取整数类型的随机数，不要将 x放大10的若干倍然后取整，直接使用Random对象的nextInt或者nextLong方法。 