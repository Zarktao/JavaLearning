## 牛客网笔试题笔记

### 取反操作符`~`
> 下面的操作将输出-11
```java 
int a = 10;
System.out.print(~a);
```

### 计算余弦值
> `Math.cos`是计算弧度的余弦值，`Math.toDegrees`是将角度转化为弧度
```java
double d=Math.cos(Math.toDegrees(42))
```

### 关于sleep()和wait()
> * sleep是线程类（Thread）的方法，wait是Object类的方法；
> * sleep不释放对象锁，wait放弃对象锁

以下来自牛客网评论，未验证正确性
> Java中的多线程是一种抢占式的机制，而不是分时机制。抢占式的机制是有多个线程处于可运行状态，但是只有一个线程在运行。 
> ##### 共同点 ： 
> 1. 他们都是在多线程的环境下，都可以在程序的调用处阻塞指定的毫秒数，并返回。 
> 2. wait()和sleep()都可以通过interrupt()方法 打断线程的暂停状态 ，从而使线程立刻抛出InterruptedException。 如果线程A希望立即结束线程B，则可以对线程B对应的Thread实例调用interrupt方法。如果此刻线程B正在wait/sleep/join，则线程B会立刻抛出InterruptedException，在catch() {} 中直接return即可安全地结束线程。 需要注意的是，InterruptedException是线程自己从内部抛出的，并不是interrupt()方法抛出的。对某一线程调用 interrupt()时，如果该线程正在执行普通的代码，那么该线程根本就不会抛出InterruptedException。但是，一旦该线程进入到 wait()/sleep()/join()后，就会立刻抛出InterruptedException 。 
> ##### 不同点 ：  
> 1. 每个对象都有一个锁来控制同步访问。Synchronized关键字可以和对象的锁交互，来实现线程的同步。 sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。 
> 2. wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用 
> 3. sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常 
> 4. sleep是线程类（Thread）的方法，导致此线程暂停执行指定时间，给执行机会给其他线程，但是监控状态依然保持，到时后会自动恢复。调用sleep不会释放对象锁。
> 5. wait是Object类的方法，对此对象调用wait方法导致本线程放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象发出notify方法（或notifyAll）后本线程才进入对象锁定池准备获得对象锁进入运行状态。

### JVM参数
> * -Xmx：最大堆大小
> * -Xms：初始堆大小
> * -Xmn:年轻代大小
> * -XXSurvivorRatio：年轻代中Eden区与Survivor区的大小比值

### List, Set, Collection & Map
> * List，Set等集合对象都继承自Collection接口
> * Map是一个顶层结果，不继承自Collection接口

### 面向对象五个基本原则
> * 单一职责原则（Single-Resposibility Principle）：一个类，最好只做一件事，只有一个引起它的变化。单一职责原则可以看做是低耦合、高内聚在面向对象原则上的引申，将职责定义为引起变化的原因，以提高内聚性来减少引起变化的原因。 
> * 开放封闭原则（Open-Closed principle）：软件实体应该是可扩展的，而不可修改的。也就是，对扩展开放，对修改封闭的。 
> * Liskov替换原则（Liskov-Substituion Principle）：子类必须能够替换其基类。这一思想体现为对继承机制的约束规范，只有子类能够替换基类时，才能保证系统在运行期内识别子类，这是保证继承复用的基础。 
> * 依赖倒置原则（Dependecy-Inversion Principle）：依赖于抽象。具体而言就是高层模块不依赖于底层模块，二者都同依赖于抽象；抽象不依赖于具体，具体依赖于抽象。 
> * 接口隔离原则（Interface-Segregation Principle）：使用多个小的专门的接口，而不要使用一个大的总接口

### JavaEE中的常见名词
> * web容器：给处于其中的应用程序组件（JSP，SERVLET）提供一个环境，使 JSP,SERVLET直接更容器中的环境变量接**互，不必关注其它系统问题。主要有WEB服务器来实现。例如：TOMCAT,WEBLOGIC,WEBSPHERE等。该容器提供的接口严格遵守J2EE规范中的WEB APPLICATION 标准。我们把遵守以上标准的WEB服务器就叫做J2EE中的WEB容器。
> * EJB容器：Enterprise java bean 容器。更具有行业领域特色。他提供给运行在其中的组件EJB各种管理功能。只要满足J2EE规范的EJB放入该容器，马上就会被容器进行高效率的管理。并且可以通过现成的接口来获得系统级别的服务。例如邮件服务、事务管理。
> * JNDI：（Java Naming & Directory Interface）JAVA命名目录服务。主要提供的功能是：提供一个目录系，让其它各地的应用程序在其上面留下自己的索引，从而满足快速查找和定位分布式应用程序的功能。
> * JMS：（Java Message Service）JAVA消息服务。主要实现各个应用程序之间的通讯。包括点对点和广播。
> * JTA：（Java Transaction API）JAVA事务服务。提供各种分布式事务服务。应用程序只需调用其提供的接口即可。
> * JAF：（Java Action FrameWork）JAVA安全认证框架。提供一些安全控制方面的框架。让开发者通过各种部署和自定义实现自己的个性安全控制策略。
> * RMI/IIOP:（Remote Method Invocation /internet对象请求中介协议）他们主要用于通过远程调用服务。例如，远程有一台计算机上运行一个程序，它提供股票分析服务，我们可以在本地计算机上实现对其直接调用。当然这是要通过一定的规范才能在异构的系统之间进行通信。RMI是JAVA特有的。

### 带return的finally
> finally语句在try或catch中的return语句执行之后返回之前执行且finally里的修改语句不能影响try或catch中 return已经确定的返回值，若finally里也有return语句则覆盖try或catch中的return语句直接返回。
> 异常处理中的finally必须在函数退出前执行

### 重载vs重写
> #### 方法重载（overload）：
> 1. 必须是同一个类
> 2. 方法名（也可以叫函数）一样
> 3. 参数类型不一样或参数数量不一样
> 4. 返回值可以不同
> #### 方法的重写（override）两同两小一大原则：
> 1. 方法名相同，参数类型相同
> 2. 子类返回类型小于等于父类方法返回类型，
> 3. 子类抛出异常小于等于父类方法抛出异常，
> 4. 子类访问权限大于等于父类方法访问权限。

### `protected` vs `default`
> protected可以给同一包中其他类访问，但是也可以给不在同一包的子类访问。default只能被同一包中的类访问

### .java源文件中的代码
> 一个java文件可以包含多个java类，但是只能包含一个public类，并且public类的类名必须与java文件名相同。

### Java中的基本类型及空间占用

|| 默认值 | 存储需求（字节）| 取值范围 | 示例 | 
| --- | --- | --- | --- | --- |
|byte |0|1|2^7—2^7-1 |byte b=10;|
|char |'\u0000' |2|0—2^16-1 |char c=’c’ ; |
|short |0|2|-2^15—2^15-1 |short s=10;| 
|int  |0|4|-2^31—2^31-1 |int i=10; |
|long |0|8|-2^63—2^63-1  |long o=10L;| 
|float  | 0.0f  |4|-2^31—2^31-1 |float f=10.0F;| 
|double  |0.0d |8|-2^63—2^63-1 |double d=10.0; |
|boolean |false  |1|true\false |boolean flag=true;|

### 静态变量可以通过对象引用

### ClassLoader
> 1. Bootstrap ClassLoader: 负责加载$JAVA_HOME中jre/lib/rt.jar里所有的class，由C++实现，不是ClassLoader子类
> 2. Extension ClassLoader: 负责加载java平台中扩展功能的一些jar包，包括$JAVA_HOME中jre/lib/*.jar或-Djava.ext.dirs指定目录下的jar包
> 3. App ClassLoader: 负责记载classpath中指定的jar包及目录中class
> 4. Custom ClassLoader: 属于应用程序根据自身需要自定义的ClassLoader，如tomcat、jboss都会根据j2ee规范自行实现ClassLoader

> 加载过程中会先检查类是否被已加载，检查顺序是自底向上，从Custom ClassLoader到BootStrap ClassLoader逐层检查，只要某个classloader已加载就视为已加载此类，保证此类只所有ClassLoader加载一次。而加载的顺序是自顶向下，也就是由上层来逐层尝试加载此类。

### Java数值默认类型
> 整数默认为int，小数默认为double

### 优化Hibernate所鼓励的7大措施：
> 1. 尽量使用many-to-one，避免使用单项one-to-many
> 2. 灵活使用单向one-to-many
> 3. 不用一对一，使用多对一代替一对一
> 4. 配置对象缓存，不使用集合缓存
> 5. 一对多使用Bag 多对一使用Set
> 6. 继承使用显示多态 HQL:from object polymorphism="exlicit" 避免查处所有对象
> 7. 消除大表，使用二级缓存

### Java中的移位符号
> * <<表示左移位
> * \>>表示带符号右移位
> * \>>>表示无符号右移
> * 没有<<<

### volatile
1. 每次从内存中取值，不从缓存中什么的拿值。这就保证了用 volatile修饰的共享变量，每次的更新对于其他线程都是可见的。
2. volatile保证了其他线程的立即可见性，就没有保证原子性。
3. 由于有些时候对 volatile的操作，不会被保存，说明不会造成阻塞。不可用与多线程环境下的计数器。

### `java.long`中不能被继承的类
> * public final class Byte
> * public final class Character
> * public static final class Character.UnicodeBlock
> * public final class Class<T>
> * public final class Compile
> * public final class Double
> * public final class Float
> * public final class Integer
> * public final class Long
> * public final class Math
> * public final class ProcessBuilder
> * public final class RuntimePermission
> * public final class Short
> * public final class StackTraceElement
> * public final class StrictMath
> * public final class String
> * public final class StringBuffer
> * public final class StringBuilder
> * public final class System
> * public final class Void


 
