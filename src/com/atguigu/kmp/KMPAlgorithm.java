package com.atguigu.kmp;

/**
 * 
 * KMP算法 实现 在一个字符串中 找到子串的第一次出现的位置 并返回否则返回-1
 * 
 * 和暴力匹配法的区别在于 KMP算法 再有N个相同字符匹配的情况下 若下一个字符不匹配时 会将指针后移n-《部分匹配表相应n下标的值》 减少了重复的判断
 * 
 * 如ABCDABD AB
 * 
 * ：ABCDABC 发现不匹配时下次直接从 ABD的A开始比较
 * 
 * @author YGQ
 *
 */
public class KMPAlgorithm {

	public static void main(String[] args) {

		String str1 = "BBC ABCDAB ABCDABCDABDE";

		String str2 = "ABCDABD";

		int[] arr = kmpNext(str2);

		int index = findIndex(str1, str2, arr);

		System.out.println(index);

	}

	/**
	 * @param str1
	 *            主字符串
	 * @param str2
	 *            子字符串
	 * @param arr
	 *            部分匹配表
	 * @return
	 */
	public static int findIndex(String str1, String str2, int[] arr) {

		for (int i = 0; i < str1.length(); i++) {

			int count = 0;

			while (i + count < str1.length() && str1.charAt(i + count) == str2.charAt(count)) {

				count++;

				if (count == str2.length()) {
					return i;
				}

			}

			// count - arr[count - 1] 向后移动的位数
			// -1是因为 for循环即将+1
			if (count != 0) {
				i = i + count - arr[count - 1] - 1;
				count = 0;
			}

		}

		return -1;

	}

	// 获取子串的部分匹配表
	public static int[] kmpNext(String str) {

		// 创建数组 保存部分匹配表的值
		int[] arr = new int[str.length()];

		for (int i = 1; i < str.length(); i++) {

			String temp = str.substring(0, i);

			for (int j = 1; j < temp.length(); j++) {

				// 左子串
				String left = temp.substring(0, j);

				// 右子串
				String right = temp.substring(temp.length() - j);

				// 如果相等
				if (left.equals(right)) {

					// 如果相同字符串的长度 大于原有的保存的长度 或 之前还没放过值 则将值放入
					if (arr[i - 1] == 0 || left.length() < arr[i - 1]) {
						arr[i - 1] = left.length();
					}
				}

			}

		}

		return arr;

	}

}
