package com.mojiayi.learn.datastructure.getnumber;

import com.mojiayi.learn.datastructure.sort.BubbleSort;

public class GetValueByIndex {
	public int getValueNumberN(int[] array, int n) {
		int length = array.length;
		int[] firstPart = new int[n];
		for (int i = 0; i < n; i++) {
			firstPart[i] = array[i];
		}
		BubbleSort bubbleSort = new BubbleSort();
		firstPart = bubbleSort.classicBubbleSort(array);
		for (int i = n; i < length; i++) {
			for (int j = n - 1; j >= 0; j--) {
				if (array[i] <= firstPart[j]) {
					int k = n - 1;
					for (; k >= j; k--) {
						firstPart[k] = firstPart[k - 1];
					}
					firstPart[k] = array[i];
				}
			}
		}
		return firstPart[n - 1];
	}
}
