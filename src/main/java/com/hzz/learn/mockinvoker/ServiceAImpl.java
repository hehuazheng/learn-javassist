package com.hzz.learn.mockinvoker;

public class ServiceAImpl implements ServiceA {

	@Override
	public String dosth(Object obj) {
		return "ServiceAImpl.dosth called";
	}

	@Override
	public void voidMethod() {
		System.out.println("void method invoked.");
	}

}
