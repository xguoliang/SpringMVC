package com.test;

import org.junit.Test;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Test
	public void test(){
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		
		System.out.println(2147483647==Integer.MAX_VALUE);
		System.out.println(-2147483648==Integer.MIN_VALUE);
		
		
	}
}
