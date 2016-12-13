package com.mojiayi.learn.datastructure.sort;

/**
 * Created by GuangRi on 2016/12/13.
 */
public class ShellSort extends MojiayiSort {
    public int[] shellSort(int[] arr, String sortType) {
        int length = arr.length;
        int i = 0;
        int j = 0;
        int increment = length / 2;
        int temp = 0;
        while (increment > 0) {
            for (i = increment; i < length; i++) {
                temp = arr[i];
                for (j = i; j >= increment; j -= increment) {
                    if (SORT_TYPE_ASC.equalsIgnoreCase(sortType)) {
                        if (temp < arr[j - increment]) {
                            arr[j] = arr[j - increment];
                        } else {
                            break;
                        }
                    } else {
                        if (temp > arr[j - increment]) {
                            arr[j] = arr[j - increment];
                        } else {
                            break;
                        }
                    }
                }
                arr[j] = temp;
            }
            increment /= 2;
        }
        return arr;
    }
}
