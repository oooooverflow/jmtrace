package org.njuws;

public class MyLog {
	public static void LogGetStatic(String owner, String name) {
		System.out.println(owner);
		System.out.println(name);
//		System.out.println(descriptor);
	}

	public static void Log(char type, Object obj, String details) {
		System.out.print(type);
		System.out.print(' ');
		System.out.print(Thread.currentThread().getId());
		System.out.print(' ');
		System.out.print(System.identityHashCode(obj));
		System.out.print(' ');
		System.out.print(details);
		System.out.println();
	}
}
