package com.atguigu.kmp;

/**
 * 暴力匹配法: 从头比对到尾
 * 
 * 判断长的字符串中是否包含短的字符串 如果有则返回第一次出现的位置 如果没有则返回-1
 * 
 * @author YGQ
 *
 */
public class ViolenceMatch {

	public static void main(String[] args) {

		String str1 = "哈哈 哈你 是哈 哈哈 我是呵呵呵";

		String str2 = "哈哈哈";

		int violenceMatch = violenceMatch(str1, str2);

		System.out.println(violenceMatch);

	}

	// 暴力匹配方法 默认str1是长字符串 str2是短字符串
	public static int violenceMatch(String str1, String str2) {

		for (int i = 0; i < str1.length(); i++) {

			// 定义一个变量 标记匹配的字符数
			int count = 0;

			while (str1.charAt(i + count) == str2.charAt(count)) {

				count++;

				if (count == str2.length()) {
					return i;
				}

			}

		}

		return -1;

	}

}
