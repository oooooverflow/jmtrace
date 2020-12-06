package org.njuws;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class Premain {
	public static void premain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException {
		System.out.println("start now!");
		inst.addTransformer(new Transformer());
	}
	public static void main( String[] args )
	{
		System.out.println( "Hello World!" );
	}
}
