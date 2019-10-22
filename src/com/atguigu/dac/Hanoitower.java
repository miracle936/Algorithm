package com.atguigu.dac;

/**
 * 分治算法 解决 汉诺塔问题
 * 
 * 思路是 将A上的圆盘分成两部分 一部分是最底下的圆盘 另一部分是其余的圆盘
 * 
 * 先将上面的圆盘全部移动到B 再把最底下的圆盘移动到C 再将B上的所有圆盘移动到C上 即可完成
 * 
 * @author YGQ
 *
 */
public class Hanoitower {

	public static void main(String[] args) {

		hanoitower(4, 'A', 'B', 'C');

	}

	/**
	 * 
	 * @param n
	 *            一共的圆盘数量
	 * @param a
	 *            起始点
	 * @param b
	 *            借助点
	 * @param c
	 *            目标点
	 */
	public static void hanoitower(int n, char a, char b, char c) {

		if (n == 1) {

			System.out.println("将第" + n + "个圆盘从" + a + "移动到" + c);

		} else {

			hanoitower(n - 1, a, c, b);

			System.out.println("将第" + n + "个圆盘从" + a + "移动到" + c);

			hanoitower(n - 1, b, a, c);

		}

	}

}
