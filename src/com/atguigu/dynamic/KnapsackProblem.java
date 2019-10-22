package com.atguigu.dynamic;

/**
 * 动态规划算法解决01背包问题 即规定背包的重量 看怎么往里添加东西使得东西的总价值最高 每种商品只能放一次 (完全背包 则是指 商品可以重复添加)
 * 
 * 动态规划算法的特点 ： 将一个大问题 分解成小问题 然后逐步解决 下一步子问题的阶段求解 都是在上一个问题的基础上进行的
 * 
 * @author YGQ
 *
 */
public class KnapsackProblem {

	public static void main(String[] args) {

		// 物品重量
		int[] w = { 1, 4, 3 };

		// 物品价格
		int[] val = { 1500, 3000, 2000 };

		// 背包可以容纳的重量
		int m = 4;

		int maxValue = getMaxValue(w, val, m);

		System.out.println("最高价值为 : " + maxValue);

	}

	/**
	 * 获取能装入的最大价值
	 * 
	 * @param w
	 *            物品重量
	 * @param val
	 *            物品价格
	 * @param capacity
	 *            背包可以容纳的重量
	 * @return
	 */
	public static int getMaxValue(int[] w, int[] val, int capacity) {

		// 创建一个二维数组用于存储各种情况下的最大值
		// + 1是为了 保留容量为0或商品数量为0的状况
		int[][] maxValues = new int[w.length + 1][capacity + 1];

		// 保存最大值 的商品放入情况
		int[][] path = new int[w.length + 1][capacity + 1];

		// 容量为0的状况
		for (int i = 0; i < maxValues.length; i++) {
			maxValues[i][0] = 0;
		}

		// 商品为0的状况
		for (int i = 0; i < maxValues[0].length; i++) {
			maxValues[0][i] = 0;
		}

		// 因为数组的行是商品 列是重量
		for (int i = 0; i < w.length; i++) {

			for (int j = 0; j < capacity; j++) {

				// 可容纳量小于商品的重量
				if (w[i] > j + 1) {
					maxValues[i + 1][j + 1] = maxValues[i][j + 1];
				} else {
					// 从上行的值 和 本商品的重量+其余重量所能装的最大价值 中选出总价值最高的
					// maxValues[i + 1][j + 1] = Math.max(maxValues[i][j + 1],
					// val[i] + maxValues[i][j + 1 - w[i]]);

					// 想获取到放入的商品情况 必须要用if else
					if (maxValues[i][j + 1] < val[i] + maxValues[i][j + 1 - w[i]]) {
						maxValues[i + 1][j + 1] = val[i] + maxValues[i][j + 1 - w[i]];
						path[i + 1][j + 1] = 1;
					} else {
						maxValues[i + 1][j + 1] = maxValues[i][j + 1];
					}

				}

			}

		}

		// 输出商品存入情况
		int i = w.length;

		int j = capacity;

		while (i > 0 && j > 0) {

			if (path[i][j] == 1) {
				System.out.println(i);

				j = j - w[i - 1];

			}

			i--;

		}

		// 这个二位数组的最后一个元素则为最大值
		return maxValues[w.length][capacity];

	}

}
