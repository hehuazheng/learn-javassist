package com.hzz.learn.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

class Hello {
	public void say() {
		System.out.println("hello");
	}
}

public class AddInterceptorBeforeMethod {

	public static void main(String[] args) throws Exception {
		ClassPool cp = ClassPool.getDefault();
		CtClass cc = cp.get("com.hzz.learn.javassist.Hello");
		CtMethod m = cc.getDeclaredMethod("say");
		m.insertBefore("{System.out.println(\"before \");}");
		Class<?> c = cc.toClass();
		Hello h = (Hello) c.newInstance();
		h.say();
	}

}
