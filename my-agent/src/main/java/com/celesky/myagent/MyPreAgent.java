package com.celesky.myagent;

import java.lang.instrument.Instrumentation;

/**
 * @desc:
 * @author: panqiong
 * @date: 2020-02-21
 */
public class MyPreAgent {
    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("premain被执行了: " + args);
        // 指定我们自己定义的 Transformer，在其中利用 Javassist 做字节码替换
//        instrumentation.addTransformer(new MyTransformer(), true);
//        try {
//            // 重定义类并载入新的字节码
//            instrumentation.retransformClasses(Base.class);
//            System.out.println("Agent Load Done.");
//        } catch (Exception e) {
//            System.out.println("agent load failed!");
//        }
    }
}
