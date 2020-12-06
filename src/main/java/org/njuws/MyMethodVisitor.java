package org.njuws;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyMethodVisitor extends MethodVisitor {
	public MyMethodVisitor(MethodVisitor mv){
		super(Opcodes.ASM9, mv);
//		System.out.println("here!");
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
		if(mv == null) {
			return;
		}
		switch(opcode) {
			case Opcodes.GETSTATIC:
//				System.out.println(MyLog.class.getCanonicalName());
				mv.visitLdcInsn(owner);
				mv.visitLdcInsn(name);
//				.replace(".", "/")
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, MyLog.class.getCanonicalName().replace(".", "/"), "LogGetStatic",
						"(Ljava/lang/String;Ljava/lang/String;)V", false);
				break;
//			case Opcodes.PUTSTATIC:
//				break;
//			case Opcodes.GETFIELD:
//				break;
//			case Opcodes.PUTFIELD:
//				break;
			default: break;
		}
//		System.out.print(opcode);
//		System.out.print(' ');
//		System.out.print(owner);
//		System.out.print(' ');
//		System.out.print(name);
//		System.out.print(' ');
//		System.out.print(descriptor);
//		System.out.println();
		super.visitFieldInsn(opcode, owner, name, descriptor);

	}

	@Override
	public void visitInsn(int opcode) {
//		System.out.println("here!");
		super.visitInsn(opcode);
//		System.out.println(opcode);
	}
}
