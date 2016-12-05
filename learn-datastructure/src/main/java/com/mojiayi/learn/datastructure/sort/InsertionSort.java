package com.mojiayi.learn.datastructure.sort;

import java.util.Arrays;

public class InsertionSort extends MojiayiSort {
    /**
     * 统计数组中的逆序个数
     * @param arr 待统计数组
     * @return 逆序个数
     */
    public int countInversion(int[] arr) {
        int count = 0;
        int length = arr.length;
        for (int i = 0;i < length;i++) {
            for (int j = i + 1;j < length;j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * 插入排序
     * @param arr 待排序数组
     * @param sortType 排序方式，参考{@link MojiayiSort}
     * @return 排序后数组
     */
    public int[] insertionSort(int[] arr, String sortType) {
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int temp = arr[i];
            int j = i;
            if (SORT_TYPE_ASC.equalsIgnoreCase(sortType)) {
                while (j > 0 && arr[j - 1] > temp) {
                    arr[j] = arr[j - 1];
                    j--;
                }
            } else {
                while (j > 0 && arr[j - 1] < temp) {
                    arr[j] = arr[j - 1];
                    j--;
                }
            }
            arr[j] = temp;
        }
        return Arrays.copyOf(arr, length);
    }
}
