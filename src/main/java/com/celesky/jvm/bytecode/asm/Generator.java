package com.celesky.jvm.bytecode.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @desc:
 * 在这个类中定义 ClassReader 和 ClassWriter，
 * 其中的逻辑是，classReader 读取字节码，然后交 给 MyClassVisitor 类处理，
 * 处理完成后由 ClassWriter 写字节码并将旧的字节码 替换掉
 * @author: panqiong
 * @date: 2020-02-20
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        // 读取
        ClassReader classReader = new ClassReader("com/celesky/jvm/bytecode/Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        // 处理
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);

        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        File f = new File("/Users/pan/myfile/intellij/javaAction/target/classes/com/celesky/jvm/bytecode/Base.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
        System.out.println("now generator code success!");
    }


}
