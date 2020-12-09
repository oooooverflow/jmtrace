package org.njuws;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class MyMethodVisitor extends MethodVisitor {
	public MyMethodVisitor(MethodVisitor mv){
		super(Opcodes.ASM7, mv);
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
		switch(opcode) {
			case Opcodes.GETSTATIC:
				mv.visitLdcInsn(owner);
				mv.visitLdcInsn(name);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(MyLog.class), "LogGetStatic",
						"(Ljava/lang/String;Ljava/lang/String;)V", false);
				break;
			case Opcodes.PUTSTATIC:
				mv.visitLdcInsn(owner);
				mv.visitLdcInsn(name);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(MyLog.class), "LogPutStatic",
						"(Ljava/lang/String;Ljava/lang/String;)V", false);
				break;
			case Opcodes.GETFIELD:
				mv.visitLdcInsn(owner);
				mv.visitLdcInsn(name);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(MyLog.class), "LogGetField",
						"(Ljava/lang/String;Ljava/lang/String;)V", false);
				break;
			case Opcodes.PUTFIELD:
				mv.visitLdcInsn(owner);
				mv.visitLdcInsn(name);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(MyLog.class), "LogPutField",
						"(Ljava/lang/String;Ljava/lang/String;)V", false);
				break;
			default: break;
		}
		super.visitFieldInsn(opcode, owner, name, descriptor);
	}

	@Override
	public void visitInsn(int opcode) {
		switch(opcode) {
			case Opcodes.IALOAD:case Opcodes.AALOAD:case Opcodes.SALOAD:case Opcodes.LALOAD:case Opcodes.FALOAD:case Opcodes.DALOAD:case Opcodes.BALOAD:case Opcodes.CALOAD:
				mv.visitInsn(Opcodes.DUP2);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(MyLog.class), "LogXaLoad",
						"(Ljava/lang/Object;I)V", false);
				break;
			case Opcodes.IASTORE:case Opcodes.AASTORE:case Opcodes.SASTORE:case Opcodes.FASTORE:case Opcodes.BASTORE:case Opcodes.CASTORE:
				mv.visitInsn(Opcodes.DUP_X2);
				mv.visitInsn(Opcodes.POP);
				mv.visitInsn(Opcodes.DUP2);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(MyLog.class), "LogXaStore",
						"(Ljava/lang/Object;I)V", false);
				mv.visitInsn(Opcodes.DUP2_X1);
				mv.visitInsn(Opcodes.POP2);
				break;
			case Opcodes.LASTORE:case Opcodes.DASTORE:
				mv.visitInsn(Opcodes.DUP2_X2);
				mv.visitInsn(Opcodes.POP2);
				mv.visitInsn(Opcodes.DUP2);
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(MyLog.class), "LogLDaStore",
						"(Ljava/lang/Object;I)V", false);
				mv.visitInsn(Opcodes.DUP2_X2);
				mv.visitInsn(Opcodes.POP2);
				break;
			default: break;
		}
		super.visitInsn(opcode);
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		super.visitMaxs(maxStack+4, maxLocals);
	}
}
