package com.mojiayi.learn.datastructure.subsequence;

import java.util.Random;

import org.junit.Test;

public class MinSubsequenceSumTest {
	@Test
	public void getMinSubsequenceSum() {
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
		MinSubsequenceSum instance = new MinSubsequenceSum();
		int minSum = instance.minSubsequenceSum(array);
		System.out.println("minSum=" + minSum);
	}
	
	@Test
	public void getMinPositiveSum() {
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
		MinSubsequenceSum instance = new MinSubsequenceSum();
		int minPositiveSum = instance.minPositiveSum(array);
		System.out.println("minPositiveSum=" + minPositiveSum);
	}
}
