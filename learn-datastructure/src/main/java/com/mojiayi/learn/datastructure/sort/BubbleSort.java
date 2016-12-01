package com.mojiayi.learn.datastructure.sort;

public class BubbleSort {
	public int[] classicBubbleSort(int[] array) {
		int length = array.length;
		for (int i = 0; i < length; i++) {
			for (int j = i; j < length; j++) {
				if (array[i] > array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
}
