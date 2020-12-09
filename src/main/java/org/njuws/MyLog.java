package org.njuws;

public class MyLog {
	public static void LogGetStatic(String className, String fieldName) {
		try {
			Object obj = Class.forName(className.replace('/','.'));
			System.out.print("R ");
			System.out.print(Thread.currentThread().getId());
			System.out.print(' ');
			System.out.print(Long.toHexString(System.identityHashCode(obj)));
			System.out.print(' ');
			System.out.print(className.replace('/','.')+'.'+fieldName+"\n");
		}catch(Exception e) {
			System.out.println("ClassName not found "+className);
			return;
		}
	}

	public static void LogPutStatic(String className, String fieldName) {
		try {
			Object obj = Class.forName(className.replace('/','.'));
			System.out.print("W ");
			System.out.print(Thread.currentThread().getId());
			System.out.print(' ');
			System.out.print(Long.toHexString(System.identityHashCode(obj)));
			System.out.print(' ');
			System.out.print(className.replace('/','.')+'.'+fieldName+"\n");
		}catch(Exception e) {
			System.out.println("ClassName not found "+className);
			return;
		}
	}

	public static void LogGetField(String className, String fieldName) {
		try {
			Object obj = Class.forName(className.replace('/','.'));
			System.out.print("R ");
			System.out.print(Thread.currentThread().getId());
			System.out.print(' ');
			System.out.print(Long.toHexString(System.identityHashCode(obj)));
			System.out.print(' ');
			System.out.print(className.replace('/','.')+'.'+fieldName+"\n");
		}catch(Exception e) {
			System.out.println("ClassName not found "+className);
			return;
		}
	}

	public static void LogPutField(String className, String fieldName) {
		try {
			Object obj = Class.forName(className.replace('/','.'));
			System.out.print("W ");
			System.out.print(Thread.currentThread().getId());
			System.out.print(' ');
			System.out.print(Long.toHexString(System.identityHashCode(obj)));
			System.out.print(' ');
			System.out.print(className.replace('/','.')+'.'+fieldName+"\n");
		}catch(Exception e) {
			System.out.println("ClassName not found "+className);
			return;
		}
	}

	public static void LogXaLoad(Object obj, int index) {
		System.out.print("R ");
		System.out.print(Thread.currentThread().getId());
		System.out.print(' ');
		System.out.print(Long.toHexString(System.identityHashCode(obj)));
		System.out.print(' ');
		System.out.print(obj.getClass().getComponentType().getCanonicalName()+"["+String.valueOf(index)+"]\n");
	}

	public static void LogXaStore(Object obj, int index) {
		System.out.print("W ");
		System.out.print(Thread.currentThread().getId());
		System.out.print(' ');
		System.out.print(Long.toHexString(System.identityHashCode(obj)));
		System.out.print(' ');
		System.out.print(obj.getClass().getComponentType().getCanonicalName()+"["+String.valueOf(index)+"]\n");
	}

	public static void LogLDaStore(Object obj, int index) {
		System.out.print("W ");
		System.out.print(Thread.currentThread().getId());
		System.out.print(' ');
		System.out.print(Long.toHexString(System.identityHashCode(obj)));
		System.out.print(' ');
		System.out.print(obj.getClass().getComponentType().getCanonicalName()+"["+String.valueOf(index)+"]\n");
	}
}
