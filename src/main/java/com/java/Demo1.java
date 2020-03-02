package com.java;

import java.lang.reflect.InvocationTargetException;

public class Demo1 {//Demo1 should be loaded into JVM? class file -> JVM
	
	A a= new A();
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
	}
	public void calc(int x, int y) {
		a.m1(x,y);
	}
	
}


class A{
	void m1(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	
}