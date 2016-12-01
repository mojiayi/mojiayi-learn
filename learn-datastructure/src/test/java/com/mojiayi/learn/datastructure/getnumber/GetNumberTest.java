package com.mojiayi.learn.datastructure.getnumber;

import java.util.Random;

import org.junit.Test;

import com.mojiayi.learn.datastructure.sort.BubbleSort;

public class GetNumberTest {
	@Test
	public void getValueNumberNTest() {
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
		BubbleSort bubbleSort = new BubbleSort();
		int[] sortedArray = bubbleSort.classicBubbleSort(array);
		System.out.print("sorted array=");
		for (int i = 0; i < n; i++) {
			System.out.print(sortedArray[i]);
			System.out.print(",");
		}
		System.out.println();
		GetValueByIndex instance = new GetValueByIndex();
		int value = instance.getValueNumberN(array, 3);
		System.out.println("valueOfNumberN=" + value);
	}
}
