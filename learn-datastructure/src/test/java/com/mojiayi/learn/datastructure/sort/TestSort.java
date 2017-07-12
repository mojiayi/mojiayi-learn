package com.mojiayi.learn.datastructure.sort;

import org.junit.Test;

public class TestSort extends MojiayiSort {
    @Test
    public void testInsertionSort() {
        int[] arr = { 34, 8, 64, 51, 32, 21 };
        InsertionSort instance = new InsertionSort();
        int[] sortedArr = instance.insertionSort(arr, SORT_TYPE_DESC);
        for (int ele : sortedArr) {
            System.out.print(ele + ",");
        }
    }

    @Test
    public void testShellSort() {
        int[] arr = { 34, 8, 64, 51, 32, 21 };
        ShellSort instance = new ShellSort();
        int[] sortedArr = instance.shellSort(arr, SORT_TYPE_ASC);
        for (int ele : sortedArr) {
            System.out.print(ele + ",");
        }
    }

    @Test
    public void testQuickSort() {
        int[] arr = { 34, 8, 64, 51, 32, 21 };
        QuickSort quickSort = new QuickSort();
        int[] sortedArr = quickSort.sort(arr, 0, arr.length - 1);
        for (int ele : sortedArr) {
            System.out.print(ele + ",");
        }
    }
}
