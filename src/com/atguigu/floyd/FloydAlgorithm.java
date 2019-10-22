package com.atguigu.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法 :计算各个点到其他点的距离
 * 
 * 思路： 借助一个中间点 计算中间点周边店互相之间的距离
 * 
 * @author YGQ
 *
 */
public class FloydAlgorithm {

	public static void main(String[] args) {

		MGraph m = new MGraph();

		floyd(m);

		for (int i = 0; i < m.weight.length; i++) {
			System.out.println(Arrays.toString(m.weight[i]));
		}

	}

	public static void floyd(MGraph m) {

		// 中间点
		for (int i = 0; i < m.verx; i++) {

			// 起始点
			for (int j = 0; j < m.verx; j++) {

				// 终点
				for (int k = 0; k < m.verx; k++) {

					// 确定j和k是i的周边点
					if (m.weight[j][i] != Integer.MAX_VALUE && m.weight[i][k] != Integer.MAX_VALUE && j != k) {

						m.weight[j][k] = Math.min(m.weight[j][k], m.weight[j][i] + m.weight[i][k]);
						m.weight[k][j] = Math.min(m.weight[j][k], m.weight[j][i] + m.weight[i][k]);

					}

				}

			}

		}

	}

}

// 村庄图(无向图)
class MGraph {

	int verx;

	char[] datas = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };

	int[][] weight;

	final int MAX_VALUE = Integer.MAX_VALUE;

	public MGraph() {

		// 初始化 无向图
		verx = datas.length;

		weight = new int[verx][verx];

		weight[0] = new int[] { 0, 5, 7, MAX_VALUE, MAX_VALUE, MAX_VALUE, 2 };
		weight[1] = new int[] { 5, 0, MAX_VALUE, 9, MAX_VALUE, MAX_VALUE, 3 };
		weight[2] = new int[] { 7, MAX_VALUE, 0, MAX_VALUE, 8, MAX_VALUE, MAX_VALUE };
		weight[3] = new int[] { MAX_VALUE, 9, MAX_VALUE, 0, MAX_VALUE, 4, MAX_VALUE };
		weight[4] = new int[] { MAX_VALUE, MAX_VALUE, 8, MAX_VALUE, 0, 5, 4 };
		weight[5] = new int[] { MAX_VALUE, MAX_VALUE, MAX_VALUE, 4, 5, 0, 6 };
		weight[6] = new int[] { 2, 3, MAX_VALUE, MAX_VALUE, 4, 6, 0 };

	}

}