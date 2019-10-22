package com.atguigu.prim;

import java.util.ArrayList;
import java.util.List;

/**
 * 普里姆算法 思路： 从已经连接上的村庄向外扩散 找到距离最短的还未连接的村庄 直到所有的村庄都连接上
 * 
 * @author YGQ
 *
 */
public class PrimAlgorithm {

	// 用于记录修路的总长度
	private static int sum;

	public static void main(String[] args) {

		MGraph m = new MGraph();

		// 返回连接村庄的下标
		List<Integer> list = prim(m, 0);

		for (Integer i : list) {
			System.out.print(m.datas[i] + "->");
		}

		System.out.println("一共修了:" + sum + "米");

	}

	/**
	 * 
	 * @param m
	 *            村庄图
	 * @param n
	 *            起点
	 * @return
	 */
	public static List<Integer> prim(MGraph m, int n) {

		// 保存已连接的村庄
		List<Integer> list = new ArrayList<>();

		list.add(n);

		// 判断村庄是否添加过
		boolean[] isvisit = new boolean[m.verx];
		isvisit[n] = true;

		// 只要连接的村庄数不足 就继续循环
		while (list.size() < m.verx) {

			// 用于记录周边距离最短的村庄的距离及下标
			int min = 0;
			int index = -1;

			// 循环已连接的村庄
			for (Integer i : list) {

				for (int j = 0; j < m.weight.length; j++) {

					// 记录距离最短 并且没添加过的村庄
					if (m.weight[i][j] > 0 && !isvisit[j] && (min == 0 || min > m.weight[i][j])) {
						min = m.weight[i][j];
						index = j;
					}

				}

			}

			// 连接村庄并记录
			list.add(index);
			isvisit[index] = true;
			sum += min;

		}

		return list;

	}

}

// 构建无向图
class MGraph {

	// 注：这里应该私有化属性的 但是因为在写案例 所以懒~~~
	// 节点个数
	int verx;

	// 节点名
	char[] datas = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };

	// 各个节点之间的距离
	int[][] weight;

	public MGraph() {

		// 初始化数据
		verx = datas.length;

		weight = new int[datas.length][datas.length];

		// 生成图
		// A-B
		weight[0][1] = 5;
		weight[1][0] = 5;

		// A-C
		weight[0][2] = 7;
		weight[2][0] = 7;

		// A-G
		weight[0][6] = 2;
		weight[6][0] = 2;

		// B-D
		weight[1][3] = 9;
		weight[3][1] = 9;

		// C-E
		weight[2][4] = 8;
		weight[4][2] = 8;

		// G-E
		weight[6][4] = 4;
		weight[4][6] = 4;

		// G-F
		weight[6][5] = 6;
		weight[5][6] = 6;

		// G-B
		weight[6][1] = 3;
		weight[1][6] = 3;

		// E-F
		weight[4][5] = 5;
		weight[5][4] = 5;

		// D-F
		weight[3][5] = 4;
		weight[5][3] = 4;

	}

}