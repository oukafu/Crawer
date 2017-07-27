package com.csdn;

import org.junit.Test;

public class HttpClientJsoupTest01Test {

	@Test
	public void getTest() {
		String url="http://www.csdn.net/";
		HttpClientJsoupTest01 test=new HttpClientJsoupTest01();
		test.get(url);
	}
}
