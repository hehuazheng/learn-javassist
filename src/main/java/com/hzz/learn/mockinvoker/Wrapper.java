package com.hzz.learn.mockinvoker;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public abstract class Wrapper {
	abstract public Object invokeMethod(Object instance, String mn,
			Class<?>[] types, Object[] args);

	private static AtomicInteger id = new AtomicInteger(0);

	public static Wrapper getWrapper(Class<?> clazz) throws Exception {
		ClassPool cp = ClassPool.getDefault();
		CtClass cc = cp.makeClass(clazz.getName() + id);
		cc.setSuperclass(cp.get(Wrapper.class.getName()));
		String methodBody = getMethodBody(clazz);
		CtMethod ctm = CtMethod.make(methodBody, cc);
		cc.addMethod(ctm);
		return (Wrapper) cc.toClass().newInstance();
	}

	private static String getMethodBody(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();
		sb.append("public Object invokeMethod(Object instance, String mn,Class[] types, Object[] args) {");
		sb.append(clazz.getName() + " w =((" + clazz.getName() + ")$1);");
		Method[] ms = clazz.getDeclaredMethods();
		for (Method m : ms) {
			sb.append("if(\"" + m.getName() + "\".equals($2)) {");
			sb.append("return w." + m.getName() + "($4[0]);}");
		}
		sb.append("return null; }");
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		ServiceAImpl sai = new ServiceAImpl();
		Wrapper w = Wrapper.getWrapper(ServiceAImpl.class);
		System.out.println(w.invokeMethod(sai, "dosth", null,
				new Object[] { "someone like you" }));
	}
}
