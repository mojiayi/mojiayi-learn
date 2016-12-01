package com.mojiayi.learn.datastructure.math;

public class CommonFactor {
	public int maximumCommonFactor(int m, int n) {
		int rem = 0;
		while (n > 0) {
			rem = m % n;
			m = n;
			n = rem;
		}
		return m;
	}
	
	public int[] allCommonFactors(int m, int n) {
		int index = 0;
		int[] commonFactors = new int[n];
		int rem = 0;
		do {
			rem = m % n;
			if (rem == 0) {
				commonFactors[index++] = n;
				n = m / n;
				m = n;
			} else {
				n = rem;
				m = n;
			}
			
		} while (index < n);
		return commonFactors;
	}
}
