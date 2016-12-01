package com.mojiayi.learn.datastructure.subsequence;

import java.util.Random;

import org.junit.Test;

import com.mojiayi.learn.datastructure.subsequence.MaxSubsequenceSum;

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
public class MaxSubsequenceSumTest {
	private MaxSubsequenceSum instance = new MaxSubsequenceSum();

	@Test
	public void maxSubsequenceSumTest() {
		int n = 10;
		int[] array = new int[n];
		Random random = new Random();
		System.out.print("array=");
		for (int i = 0; i < n; i++) {
			array[i] = random.nextInt(100) - 50;
			System.out.print(array[i]);
			System.out.print(",");
		}
		System.out.println();
		int maxSubsequenceSum = instance.maxSubsequenceSum1(array);
		int maxSubsequenceSum2 = instance.maxSubsequenceSum2(array);
		int maxSubsequenceSum3 = instance.maxSubsequenceSum3(array, 0, n - 1);
		int maxSubsequenceSum4 = instance.maxSubsequenceSum4(array);
		System.out.println("maxSubsequenceSum=" + maxSubsequenceSum);
		System.out.println("maxSubsequenceSum2=" + maxSubsequenceSum2);
		System.out.println("maxSubsequenceSum3=" + maxSubsequenceSum3);
		System.out.println("maxSubsequenceSum4=" + maxSubsequenceSum4);
		Assert.assertEquals(maxSubsequenceSum, maxSubsequenceSum2);
		Assert.assertEquals(maxSubsequenceSum, maxSubsequenceSum3);
		Assert.assertEquals(maxSubsequenceSum, maxSubsequenceSum4);
	}

	@Test
	public void maxSubsequenceTest() {
		int n = 10;
		Integer[] array = new Integer[n];
		Random random = new Random();
		System.out.print("array=");
		for (int i = 0; i < n; i++) {
			array[i] = random.nextInt(100) - 50;
			System.out.print(array[i]);
			System.out.print(",");
		}
		System.out.println();
		System.out.print("max subsequence=");
		int[] indexArr = instance.maxSubsequence1(array);
		int sum = 0;
		for (int i = indexArr[0]; i <= indexArr[1]; i++) {
			System.out.print(array[i]);
			System.out.print(",");
			sum += array[i];
		}
		System.out.println();
		System.out.print("max subsequence2=");
		int[] indexArr2 = instance.maxSubsequence2(array);
		int sum2 = 0;
		for (int i = indexArr2[0]; i <= indexArr2[1]; i++) {
			System.out.print(array[i]);
			System.out.print(",");
			sum2 += array[i];
		}
		System.out.println();
		Assert.assertEquals(sum2, sum);
	}
}
