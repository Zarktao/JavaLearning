## serializable接口有哪些必须实现的方法
没有定义必须实现的方法，这个接口只作为能否序列化的一个标志

## transient
Java的serialization提供了一种持久化对象实例的机制。当持久化对象时，可能有一个特殊的对象数据成员，我们不想用serialization机制来保存它。为了在一个特定对象的一个域上关闭serialization，可以在这个域前加上关键字transient。当一个对象被序列化的时候，transient型变量的值不包括在序列化的表示中，然而非transient型的变量是被包括进去的。

## violate关键词的作用
只保证从内存中读取最新值，但不保证操作的原子性

## 事务的四个特性
* A原子性：要么全部成功，要么全部回滚
* C一致性：从一个一致性状态转换到另一个一致性状态
* I隔离性：并发事务之间相互隔离
* D持久性：事务提交后操作会永久生效

## synchronize static和不加static的区别
synchronize是对类的实例加锁，synchronize static是对该类的所有实例加锁

## survivor区作用
Survivor的作用是减少被送到老年代的对象，进而减少Full GC的发生，Survivor的预筛选保证，只有经历16次Minor GC还能在新生代中存活的对象，才会被送到老年代。

## synchronized和Lock的区别
1. Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
2. synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
3. Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
4. 通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
5. Lock可以提高多个线程进行读操作的效率。在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，在具体使用时要根据适当情况选择。

## 可重入锁、可中断锁、公平锁、读写锁
* 可重入：已持有锁的对象，在调用被同样锁定的方法时，不需要重新申请自己所持有的锁
* 可中断锁：等待获取锁的过程可被中断
* 公平锁：尽量以请求锁的顺序来获取锁
* 读写锁：将读和写的功能分成两个锁

## import static
导入静态方法

## 常见的防止内存泄漏的方法
1. 注意集合类，例如HashMap，ArrayList，等等。因为它们是内存泄漏经常发生的地方。当它们被声明为静态时，它们的生命周期就同应用程序的生命周期一般长。
2. 注意事件监听器和回调，如果一个监听器已经注册，但是当这个类不再被使用时却未被注销，就会发生内存泄漏。
3. “如果一个类管理它自己的内存，程序员应该对内存泄漏保持警惕。”很多时候当一个对象的成员变量指向其他对象时，不再使用时需要被置为null。

## 范式
* 第一范式: 确保每列的原子性. 
* 第二范式: 确保表中的每列都和主键相关. 
* 第三范式: 确保每列都和主键列直接相关,而不是间接相关. 
* BCNF: 其他属性与候选主键之间没有传递依赖