package com.mojiayi.learn.datastructure.subsequence;

public class MaxSubsequenceSum {
	public int maxSubsequenceSum1(int[] array) {
		int tempSum = 0;
		int maxSum = 0;
		int length = array.length;
		for (int i = 0; i < length; i++) {
			for (int j = i; j < length; j++) {
				tempSum = 0;
				for (int k = i; k <= j; k++) {
					tempSum += array[k];
				}
				maxSum = tempSum > maxSum ? tempSum : maxSum;
			}
		}
		return maxSum;
	}

	public int maxSubsequenceSum2(int[] array) {
		int tempSum = 0;
		int maxSum = 0;
		int length = array.length;
		for (int i = 0; i < length; i++) {
			tempSum = 0;
			for (int j = i; j < length; j++) {
				tempSum += array[j];
				maxSum = tempSum > maxSum ? tempSum : maxSum;
			}
		}
		return maxSum;
	}

	public int maxSubsequenceSum3(int[] array, int left, int right) {
		if (left == right) {
			if (array[left] > 0) {
				return array[left];
			} else {
				return 0;
			}
		}
		int center = (left + right) / 2;
		int maxLeftSum = maxSubsequenceSum3(array, left, center);
		int maxRightSum = maxSubsequenceSum3(array, center + 1, right);
		int leftBorderSum = 0;
		int maxLeftBorderSum = 0;
		for (int i = center; i >= left; i--) {
			leftBorderSum += array[i];
			maxLeftBorderSum = leftBorderSum > maxLeftBorderSum ? leftBorderSum : maxLeftBorderSum;
		}
		int rightBorderSum = 0;
		int maxRightBorderSum = 0;
		for (int i = center + 1; i <= right; i++) {
			rightBorderSum += array[i];
			maxRightBorderSum = rightBorderSum > maxRightBorderSum ? rightBorderSum : maxRightBorderSum;
		}
		return Math.max(Math.max(maxLeftSum, maxRightSum), leftBorderSum + rightBorderSum);
	}

	public int maxSubsequenceSum4(int[] array) {
		int maxSum = 0;
		int tempSum = 0;
		for (int i = 0; i < array.length; i++) {
			tempSum += array[i];
			if (tempSum > maxSum) {
				maxSum = tempSum;
			} else if (tempSum < 0) {
				tempSum = 0;
			}
		}
		return maxSum;
	}

	public int[] maxSubsequence1(Integer[] array) {
		int tempSum = 0;
		int maxSum = 0;
		int length = array.length;
		int startIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < length; i++) {
			for (int j = i; j < length; j++) {
				tempSum = 0;
				int k = i;
				for (; k <= j; k++) {
					tempSum += array[k];
				}
				if (tempSum > maxSum) {
					maxSum = tempSum;
					startIndex = i;
					endIndex = k - 1;
				}
			}
		}
		int[] indexArr = { startIndex, endIndex, maxSum };
		return indexArr;
	}

	public int[] maxSubsequence2(Integer[] array) {
		int tempSum = 0;
		int maxSum = 0;
		int length = array.length;
		int startIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < length; i++) {
			tempSum = 0;
			for (int j = i; j < length; j++) {
				tempSum += array[j];
				if (tempSum > maxSum) {
					maxSum = tempSum;
					startIndex = i;
					endIndex = j;
				}
			}
		}
		int[] indexArr = { startIndex, endIndex, maxSum };
		return indexArr;
	}
}
