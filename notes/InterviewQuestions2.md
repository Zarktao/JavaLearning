## 构建平衡二叉树
旋转的方式和算法[参考](http://blog.csdn.net/realxuejin/article/details/12872035)

## 产生死锁的四个必要条件
1. 互斥条件：每个资源只能同时被一个线程使用
2. 请求和保持条件：进程获得资源后在对其他资源发出请求，该资源可能被其他线程持有，则请求阻塞同时对已持有的资源不释放
3. 不可剥夺条件：线程持有的资源在未使用完成时不可被剥夺，只能使用完成后主动释放
4. 循环等待条件：发生死锁后必然存在进程和资源之间的环形等待关系

## 三次握手&四次挥手过程及原因
![](./images/TCPHandShake.jpg)

> ### 为什么要三次握手（[参考链接](http://www.jellythink.com/archives/705)）
> **为了防止已失效的连接请求报文段突然又传送到了服务端，因而产生错误。**例如：“已失效的连接请求报文段”的产生在这样一种情况下：client发出的第一个连接请求报文段并没有丢失，而是在某个网络结点长时间的滞留了，以致延误到连接释放以后的某个时间才到达server。本来这是一个早已失效的报文段。但server收到此失效的连接请求报文段后，就误认为是client再次发出的一个新的连接请求。于是就向client发出确认报文段，同意建立连接。假设不采用“三次握手”，那么只要server发出确认，新的连接就建立了。由于现在client并没有发出建立连接的请求，因此不会理睬server的确认，也不会向server发送数据。但server却以为新的运输连接已经建立，并一直等待client发来数据。这样，server的很多资源就白白浪费掉了。采用“三次握手”的办法可以防止上述现象发生。例如刚才那种情况，client不会向server的确认发出确认。server由于收不到确认，就知道client并没有要求建立连接。”
> ### 为什么要四次挥手（[参考链接](http://www.jellythink.com/archives/705)）
> 那四次分手又是为何呢？TCP协议是一种面向连接的、可靠的、基于字节流的运输层通信协议。TCP是全双工模式，这就意味着，当主机1发出FIN报文段时，只是表示主机1已经没有数据要发送了，主机1告诉主机2，它的数据已经全部发送完毕了；但是，这个时候主机1还是可以接受来自主机2的数据；当主机2返回ACK报文段时，表示它已经知道主机1没有数据发送了，但是主机2还是可以发送数据到主机1的；当主机2也发送了FIN报文段时，这个时候就表示主机2也没有数据要发送了，就会告诉主机1，我也没有数据要发送了，之后彼此就会愉快的中断这次TCP连接。如果要正确的理解四次分手的原理，就需要了解四次分手过程中的状态变化。
> * FIN_WAIT_1: 这个状态要好好解释一下，其实FIN_WAIT_1和FIN_WAIT_2状态的真正含义都是表示等待对方的FIN报文。而这两种状态的区别是：FIN_WAIT_1状态实际上是当SOCKET在ESTABLISHED状态时，它想主动关闭连接，向对方发送了FIN报文，此时该SOCKET即进入到FIN_WAIT_1状态。而当对方回应ACK报文后，则进入到FIN_WAIT_2状态，当然在实际的正常情况下，无论对方何种情况下，都应该马上回应ACK报文，所以FIN_WAIT_1状态一般是比较难见到的，而FIN_WAIT_2状态还有时常常可以用netstat看到。（主动方）
> * FIN_WAIT_2：上面已经详细解释了这种状态，实际上FIN_WAIT_2状态下的SOCKET，表示半连接，也即有一方要求close连接，但另外还告诉对方，我暂时还有点数据需要传送给你(ACK信息)，稍后再关闭连接。（主动方）
> * CLOSE_WAIT：这种状态的含义其实是表示在等待关闭。怎么理解呢？当对方close一个SOCKET后发送FIN报文给自己，你系统毫无疑问地会回应一个ACK报文给对方，此时则进入到CLOSE_WAIT状态。接下来呢，实际上你真正需要考虑的事情是察看你是否还有数据发送给对方，如果没有的话，那么你也就可以 close这个SOCKET，发送FIN报文给对方，也即关闭连接。所以你在CLOSE_WAIT状态下，需要完成的事情是等待你去关闭连接。（被动方）
> * LAST_ACK: 这个状态还是比较容易好理解的，它是被动关闭一方在发送FIN报文后，最后等待对方的ACK报文。当收到ACK报文后，也即可以进入到CLOSED可用状态了。（被动方）
> * TIME_WAIT: 表示收到了对方的FIN报文，并发送出了ACK报文，就等2MSL后即可回到CLOSED可用状态了。如果FINWAIT1状态下，收到了对方同时带FIN标志和ACK标志的报文时，可以直接进入到TIME_WAIT状态，而无须经过FIN_WAIT_2状态。（主动方）
> * CLOSED: 表示连接中断。

## HashMap的实现原理
参考链接[Java集合学习1：HashMap的实现原理](http://tracylihui.github.io/2015/07/01/Java%E9%9B%86%E5%90%88%E5%AD%A6%E4%B9%A01%EF%BC%9AHashMap%E7%9A%84%E5%AE%9E%E7%8E%B0%E5%8E%9F%E7%90%86/)

## LinkedList和ArrayList的区别
* **实现**: ArrayList和Vector使用了数组的实现。LinkedList使用了循环双向链表数据结构。
* **直接插入元素**: ArrayList增加元素会先确保数组大小，若数组大小不足会首先扩容到原始大小的1。5倍再插入数组末尾。LinkedList会直接将元素放在链表尾部。
* **插入到任意位置**: ArrayList的每次插入都会导致之后的元素进行位置重新排序。LinkedList的插入和直接插入效率相同。
* **删除操作和插入类似**
* **容量参数**: 容量参数是ArrayList和Vector等基于数组的List的特有性能参数。它表示初始化的数组大小。当ArrayList所存储的元素数量超过其已有大小时。它便会进行扩容，数组的扩容会导致整个数组进行一次内存复制。因此合理的数组大小有助于减少数组扩容的次数，从而提高系统性能。
* **遍历**: 对于ArrayList使用index的for循环遍历效率最高；LinkedList使用迭代器效率最高

## JVM结构
参考[浅析Java虚拟机结构与机制](http://blog.hesey.net/2011/04/introduction-to-java-virtual-machine.html)

## 临界区
临界区指的是一个访问共用资源（例如：共用设备或是共用存储器）的程序片段，而这些共用资源又无法同时被多个线程访问的特性。当有线程进入临界区段时，其他线程或是进程必须等待（例如：bounded waiting 等待法），有一些同步的机制必须在临界区段的进入点与离开点实现，以确保这些共用资源是被互斥获得使用，例如：semaphore。