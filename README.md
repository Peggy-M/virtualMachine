## JVM 命令以及执行引擎

### 操作命令参数

- 查询堆详细情况
~~~ shell
PS C:\Users\32094> jmap -heap 25792
Attaching to process ID 25792, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.361-b09

using thread-local object allocation.
Parallel GC with 8 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 0
   MaxHeapFreeRatio         = 100
   MaxHeapSize              = 4238344192 (4042.0MB)
   NewSize                  = 88604672 (84.5MB)
   MaxNewSize               = 1412431872 (1347.0MB)
   OldSize                  = 177733632 (169.5MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 0 (0.0MB)

Heap Usage:
PS Young Generation
Eden Space:
   capacity = 66584576 (63.5MB)
   used     = 9324304 (8.892349243164062MB)
   free     = 57260272 (54.60765075683594MB)
   14.003699595533957% used
From Space:
   capacity = 11010048 (10.5MB)
   used     = 0 (0.0MB)
   free     = 11010048 (10.5MB)
   0.0% used
To Space:
   capacity = 11010048 (10.5MB)
   used     = 0 (0.0MB)
   free     = 11010048 (10.5MB)
   0.0% used
PS Old Generation
   capacity = 177733632 (169.5MB)
   used     = 0 (0.0MB)
   free     = 177733632 (169.5MB)
   0.0% used

2249 interned Strings occupying 208120 bytes.
~~~

### 内存溢出的实验
命令参数

~~~ shell
# 设置堆溢出报错时,将错误输出到 heap.hprof 文件
-XX:+HeapDumpOnOutOfMemoryError     -XX:HeapDumpPath=heap.hprof 
# 设置最大的堆内存大小,以及初始化时的堆内存大小
-Xms20M -Xmx20M
~~~

![image-20250708162335719](README.assets/image-20250708162335719.png)

调用接口测试

~~~ http
http://localhost:8080/heap
~~~

### 优化调试步骤

首先对于执行引擎采用混合模式，对于编写的代码会编译器编译成 .class 文件，然后由解释器去逐行解释执行，JVM 内部做了优化，对于热点代码，也就是被频繁调用解释执行的代码被即时编译器编译成可执行文件，对于这些热点代码会被编译成与本地机器平台相关联的机器码并缓存在内存当中，需要的时候直接执行。对于平时开发锁用到的 jdk 默认都是 mixed mode 模式。

![image-20250708164249305](README.assets/image-20250708164249305.png) 

~~~ shell
#通过参数 -Xint 可以在运行的时候进行设置
PS C:\Users\32094> java -Xint -verison
Unrecognized option: -verison
Error: Could not create the Java Virtual Machine.
Error: A fatal exception has occurred. Program will exit.
~~~

![image-20250708164633808](README.assets/image-20250708164633808.png) 