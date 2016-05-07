package com.hzz.learn.javassist;

public abstract class SuperInterfaceImpl implements SuperInterface {
	abstract void tt();
	
	@Override
	public String dosth() {
		return "impl;";
	}
}
