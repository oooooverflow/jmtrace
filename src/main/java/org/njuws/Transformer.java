package org.njuws;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;


public class Transformer implements ClassFileTransformer {
	public byte[] transform(  ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		ClassReader cr = new ClassReader(classfileBuffer);
		ClassWriter cw = new ClassWriter(cr, 0);
		MyClassVisitor cv = new MyClassVisitor(cw);
		cr.accept(cv, 0);
		return cw.toByteArray();
	}
}