package com.ping.erp.common.util;

import org.junit.jupiter.api.Test;

class StringUtilTest {

	@Test
	void testGetUUID() {
		for (int i = 0; i < 10; i++) {
			System.out.println(StringUtil.getUUID());
		}
	}

	@Test
	void testGetMD5() {

	}

}
