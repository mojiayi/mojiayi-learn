package com.mojiayi.learn.datastructure.math;

import org.junit.Test;

import com.mojiayi.learn.datastructure.math.CommonFactor;

import junit.framework.Assert;

public class TestCommonFactor {
	@Test
	public void testMaximumCommonFactor() {
		CommonFactor instance = new CommonFactor();
		int m = 15;
		int n = 50;
		int rem = instance.maximumCommonFactor(m, n);
		Assert.assertEquals(5, rem);
	}
	
	@Test
	public void testAllCommonFactors() {
		CommonFactor instance = new CommonFactor();
		int m = 50;
		int n = 10;
		int[] commonFactors = instance.allCommonFactors(m, n);
		for (int commonFactor : commonFactors) {
			System.out.print(commonFactor);
			System.out.print(",");
		}
	}
}
