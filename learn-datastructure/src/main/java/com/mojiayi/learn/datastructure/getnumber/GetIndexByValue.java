package com.mojiayi.learn.datastructure.getnumber;

public class GetIndexByValue {
	public int binarySearch(int[] array, int value) {
		int length = array.length;
		int low = 0;
		int mid = 0;
		int high = length - 1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (array[mid] < value) {
				low = mid + 1;
			} else if (array[mid] > value) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
}
