# java.util.concurrent.ExecutorService.java

Executor提供了：

1. 管理线程的结束的方法
2. 返回Future对象，用于监控一个或多个异步任务是否结束

一个`ExecutorService`可以被关闭 *(termination)*，关闭后无法添加新的任务。提供了两种关闭的方式：

1. `shutdown()`会将之前提交的任务执行完毕后再关闭`ExecutorService`
2. `shutdownNow()`将不会执行在队列中等待的任务，并且会尝试停止现在正在运行的任务

当`ExecutorService`关闭时，当前没有任务在执行状态，也没有任务在等待状态，并且没有新任务可以提交到线程池中。一个未被使用的线程池应该及时关闭以释放资源。

`submit()`方法是继承了`Executor.execute(Runnable r)`方法，可以提交一个待执行的任务，并得到一个`Future`对象。`Future`对象可以用来取消任务的执行或等待任务完成。

`invokeAny()`或`invokAll()`方法，可以批量执行任务，并且等待其中一个或全部执行完成。`ExecutorCompletionService`类可以用来创建这些方法的变体。

`Executors`类提供了线程池的工厂方法。

下面是在JavaDoc中提供的代码示例：

第一段代码是一个简单的网络服务的实现

```java
class NetworkService implements Runnable {
  private final ServerSocket serverSocket;
  private final ExecutorService pool;
  public NetworkService(int port, int poolSize)
      throws IOException {
    serverSocket = new ServerSocket(port);
    pool = Executors.newFixedThreadPool(poolSize);
  }
  public void run() { // run the service
    try {
      for (;;) {
        pool.execute(new Handler(serverSocket.accept()));
      }
    } catch (IOException ex) {
      pool.shutdown();
    }
  }
}
class Handler implements Runnable {
  private final Socket socket;
  Handler(Socket socket) { this.socket = socket; }
  public void run() {
    // read and service request on socket
  }
}}
```

下面这段代码展示了两阶段关闭线程池的方式，首先调用`shutdown`来拒绝新任务，然后调用`shutdownNow`来停止正在执行的任务

```java
void shutdownAndAwaitTermination(ExecutorService pool) {
  pool.shutdown(); // Disable new tasks from being submitted
  try {
    // Wait a while for existing tasks to terminate
    if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
      pool.shutdownNow(); // Cancel currently executing tasks
      // Wait a while for tasks to respond to being cancelled
      if (!pool.awaitTermination(60, TimeUnit.SECONDS))
          System.err.println("Pool did not terminate");
    }
  } catch (InterruptedException ie) {
    // (Re-)Cancel if current thread also interrupted
    pool.shutdownNow();
    // Preserve interrupt status
    Thread.currentThread().interrupt();
  }
}}
```

### `void shutdown()`
* 发起一个顺序的关闭线程池的动作。之前提交的任务仍然会被执行，但是新提交的任务不会被接受。
* 对一个已关闭的线程池调用此方法无效果。
* 这个方法不会同步等待所有任务结束才返回，可以使用`awaitTermination`来完成此操作。
* **TODO** `SecurityException` 如果存在security manager并且尝试关闭线程池，`ExecutorService`有可能去操作调用者不被允许操作的线程
* **TODO** `java.lang.RuntimePermission` 修改线程或security manager拒接访问

### `List<Runnable> shutdownNow()`
* 尝试停止所有正在执行的任务，停止正在等待的任务，并返回在队列中等待的任务列表
* 这个方法不会同步等待所有任务结束才返回，可以使用`awaitTermination`来完成此操作。
* **不保证**能够停止所有正在执行的任务。例如常用的实现是`Thread.interrupt`，如果有线程没有响应上述方法，那么该线程可能不会被停止
* 异常同`shutdown()`

### `boolean isShutdown();`
* 如果线程池被关闭则返回true

### `boolean isTerminated();`
* 在线程池关闭后是否所有的任务都执行结束了
* 必须调用过`shutdown`或`shutdownNow`之后才有可能返回true

### `boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;`
* 阻塞当前线程，直到所有的任务在线程池关闭请求之后都执行结束 或 超时 或 当前线程被打断
* `timeout` 等待最大时间
* `unit` timeout的单位
* `InterruptedException` 若当前线程被打断则抛出异常

### `<T> Future<T> submit(Callable<T> task);`
* 提交一个有值返回的任务 *Callable* 到线程池中并执行。`Future`对象中的`get`方法会返回结果集如果任务成功执行完成。
* 如果想在任务提交之后立即阻塞当前线程的执行，可以这样写`result = exec.submit(aCallable).get();`
* `RejectedExecutionException` 如果任务无法被安排执行
* `NullPointerException` 如果任务为空
> **TODO** `Executors`类中包含了一系列方法可以转换closure-like objects。例如,将`java.security.PrivilegedAction`转换为`Callable`

### `<T> Future<T> submit(Runnable task, T result);`
* 参数中的result是用来返回结果的
* 异常同上
> 另一个类似的方法`Future<?> submit(Runnable task);`
> `Callable`和`Runnable`接口类似，不同的是后者可返回结果集，通过`Future`的`Get`方法获得

### `<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException;`
* 用线程池执行参数中传递的这些任务并阻塞直到任务 *完成* *(complete)*  后返回对应的`Future`对象数组。
* 返回的`List`中的`Future.isDone()`方法一定返回`true`
* 如果在这个方法正在调用过程中，`tasks`变量数组被修改，那么这个方法会返回undefined
* 返回的list顺序和参数中任务`List#Iterator`的顺序是对应的
* `InterruptedException`, `NullPointerException`, `RejectedExecutionException`出现条件同上
> “完成” "Complete"的任务是指任务正常完成或抛出异常结束

### `<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException;`
* 和上面的方法类似，多了时间限制，当任务超时时，返回结果`Future.isDone`返回`true`
* 方法返回的时机：执行结束、抛出异常和超时
* `InterruptedException`, `NullPointerException`, `RejectedExecutionException`出现条件同上

### `<T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException;`
* 执行参数中给出的任务，当其中任何一个任务成功完成（不包括抛出异常）时返回
* 在正常或异常返回时，未执行完的任务将会被结束
* 参数中传递的数组依旧不能被修改
* `InterruptedException`, `NullPointerException`, `RejectedExecutionException`出现条件同上
* `ExecutionException` 没有任何任务成功结束
* `IllegalArgumentException` 任务数组为空

### `<T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;`
* 任务成功完成或超时时返回
* 异常同上