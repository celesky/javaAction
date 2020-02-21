package com.celesky.jvm.bytecode.instruments;

import java.lang.instrument.Instrumentation;

/**
 * @desc: 这里用到了instrument包下面的Instrumentation类
 * 可以做启动后的 Instrument、本地代码(Native Code) 的 Instrument，以及动态改变 Classpath 等等
 * @author: panqiong
 * @date: 2020-02-21
 */
public class MyAgent {


    public static void agentmain(String args, Instrumentation instrumentation) {
        // 指定我们自己定义的 Transformer，在其中利用 Javassist 做字节码替换
        instrumentation.addTransformer(new MyTransformer(), true);
        try {
            // 重定义类并载入新的字节码
            instrumentation.retransformClasses(Base.class);
            System.out.println("Agent Load Done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }
    }
}
