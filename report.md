# Jmtrace Report

### MF20330082 王森

### 文件构成

- src/main/java/org/njuws/
  ├── MyClassVisitor.java
  ├── MyLog.java
  ├── MyMethodVisitor.java
  ├── Premain.java
  └── Transformer.java

## 算法分析与实现

主要通过字节码分析工具 ASM和 java.lang.Instrument对使用字节码进行插桩，在 Instrumentation 的实现当中，存在一个 JVMTI 的代理程序，通过调用 JVMTI 当中 Java 类相关的函数来完成 Java 类的动态操作。因为本实验在程序启动之时启动代理(pre-main)程序，在被测程序读写内存时修改其字节码。

为了使插桩不改变源程序的行为，必须保护和复原程序现场（主要是维护Operand Stack状态在插桩前后不变）。

- 为了避免处理过多库函数中的内存读写操作，首先在代理入口函数中判断当前className是否时以java或者sun开头，进行过滤。

![image-20201209212220225](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20201209212220225.png)

- 自定义ClassReader，ClassWriter和ClassVisitor，关联后，ClassWriter修改返回的字节码即为插桩完成的结果。

![image-20201209212452252](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20201209212452252.png)

- 在自定义的ClassVIsitor类中重载visitMethod函数，修改返回的MethodVisitor类为自定义的MyMethodVisitor类：

![image-20201209212728556](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20201209212728556.png)

- 着重介绍自定义的MyMethodVisitor类，介绍重载的两个函数visitFieldInsn和visitInsn

  - 打印函数作为静态函数加载到JVM中，插桩时在栈中准备对应打印函数需要的参数，然后通过InvokeStatic指令调用打印函数。

  - 在visitFieldInsn中会接收到GETSTATIC，PUTSTATIC，GETFIELD以及PUTFIELD四个访存指令，分别进行处理，打印相关信息。这四个指令都不需要使用栈中的信息，通过指令附近的参数即可打印需要的信息，所以通过visitLdcInsn函数把对象和类名压入栈中并invoke对应的打印函数即可，以GETSTATIC为例如下：

    ![image-20201209213808263](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20201209213808263.png)

    ![image-20201209213910171](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20201209213910171.png)

  - 在visitInsn函数中需要处理与数组相关的读写操作\*ALOAD和\*STORE指令，前者是都内存指令，后者是写内存指令。\*中字符的不同，访存涉及到的对象的类型也不一样，其中除了long和double类型使用两个slot存储之外，其他数据类型在栈中都是使用一个slot存储，因此在处理Long和Double类型时，对象的value在栈中占据两个slot空间，以IASTORE为例，为int数据类型。处理int类型时，栈中的数据变化如下图所示：

    - ![未命名文件 (1)](C:\Users\ASUS\Downloads\未命名文件 (1).png)
    - 在处理long和double类型时，上述value占据空间为2个slots，因此使用的Opcodes指令也有所不同，以LASTORE为例，栈中数据变化如下：



##  实验结果展示

