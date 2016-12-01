package com.mojiayi.learn.datastructure.subsequence;

public class MinSubsequenceSum {
	public int minSubsequenceSum(int[] array) {
		int minSum = 0;
		int tempSum = 0;
		for (int i = 0; i < array.length; i++) {
			tempSum += array[i];
			if (tempSum < minSum) {
				minSum = tempSum;
			} else if (tempSum > 0) {
				tempSum = 0;
			}
		}
		return minSum;
	}
	
	public int minPositiveSum(int[] array) {
		int minSum = Integer.MAX_VALUE;
		int tempSum = 0;
		for (int i = 0; i < array.length; i++) {
			tempSum += array[i];
			if (tempSum < 0) {
				continue;
			}
			if (tempSum < minSum) {
				minSum = tempSum;
			}
		}
		return minSum;
	}
}
