package com.hzz.learn.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		String newClassName = "test";
		CtClass cc = pool.makeClass(newClassName);
		cc.setSuperclass(pool.get(SuperInterfaceImpl.class.getName()));
		CtConstructor ctor = CtNewConstructor.make("public " + newClassName
				+ "(){}", cc);
		cc.addConstructor(ctor);
		CtMethod mthd = CtNewMethod.make(
				"public String dosth() {return \"hzz\";}", cc);
		cc.addMethod(mthd);
		CtMethod mthd2 = CtNewMethod.make("public void tt(){}",cc);
		cc.addMethod(mthd2);
		Class<?> clazz = cc.toClass();
		SuperInterface si = (SuperInterface) clazz.newInstance();
		System.out.println(si.dosth());
	}
}
