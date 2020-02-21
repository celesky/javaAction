package com.celesky.jvm.bytecode.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @desc: 对字节码的 visit 以及修改
 * @author: panqiong
 * @date: 2020-02-20
 */
public class MyClassVisitor extends ClassVisitor implements Opcodes {
    public MyClassVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }
    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) { cv.visit(version, access, name, signature, superName,
            interfaces);
    }

    /**
     * 通过 MyClassVisitor 类中的 visitMethod 方法，判断当前字节码读到 哪一个方法了
     * 跳过构造方法 <init> 后，将需要被增强的方法交给内部类 MyMethodVisitor 来进行处理
     * @param access
     * @param name
     * @param desc
     * @param signature
     * @param exceptions
     * @return
     */
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature,
                                     String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        // Base 类中有两个方法:无参构造以及 process 方法，
        // 这里不增强构造方法 if (!name.equals("<init>") && mv != null) {
        System.out.println("遍历方法:"+name);
        if (!name.equals("<init>") && mv != null) {
            mv = new MyMethodVisitor(mv);
        }
        return mv;
    }

    class MyMethodVisitor extends MethodVisitor implements Opcodes {
        public MyMethodVisitor(MethodVisitor mv) {
            super(Opcodes.ASM5, mv);
        }


        /**
         * visitCode 方法，它会在 ASM 开始访问某一个方法的 Code 区时被调用，
         * 重写 visitCode 方法，将 AOP 中 的前置逻辑就放在这里
         */
        @Override
        public void visitCode() {
            super.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
                    "Ljava/io/PrintStream;");
            mv.visitLdcInsn("start");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }

        /**
         * MyMethodVisitor 继续读取字节码指令，
         * 每当 ASM 访问到无参数指令时，都 会调用 MyMethodVisitor 中的 visitInsn 方法
         * 通过调用 methodVisitor 的 visitXXXXInsn() 方法就可以实现字节码的插入，
         * XXXX 对 应相应的操作码助记符类型，比如 mv.visitLdcInsn(“end”) 对应的操作码就 是 ldc“end”，
         * 即将字符串“end”压入栈
         * @param opcode
         */
        @Override
        public void visitInsn(int opcode) {
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)|| opcode == Opcodes.ATHROW) {
                // 方法在返回之前，打印 "end"
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("end");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false); }
                mv.visitInsn(opcode);
        }
    }

}
