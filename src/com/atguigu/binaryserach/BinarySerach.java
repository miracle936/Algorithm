package com.atguigu.binaryserach;

/**
 * 二分查找法
 * 
 * @author YGQ
 *
 */
public class BinarySerach {

	public static void main(String[] args) {

		// 二分查找法要求数组有序
		int[] arr = { 1, 3, 8, 10, 11, 67, 100 };

		int index = serach(arr, 67);

		System.out.println("下标为:" + index);

	}

	public static int serach(int[] arr, int value) {

		int left = 0;

		int right = arr.length - 1;

		int mid = (left + right) >>> 1;

		while (left <= right) {

			if (arr[mid] < value) {
				left = mid + 1;
			} else if (arr[mid] > value) {
				right = mid - 1;
			} else {
				return mid;
			}

			mid = (left + right) >>> 1;

		}

		return -1;

	}

}
