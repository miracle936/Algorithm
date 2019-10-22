package com.atguigu.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 迪杰斯特拉算法
 * 
 * 求出从出发点开始到其他各个点的最小距离
 * 
 * 思路：利用图的广度优先遍历法 先计算起始点周围的点的最短距离 再向外扩散
 * 
 * 
 * @author YGQ
 *
 */
public class DijkstraAlgorithm {

	public static void main(String[] args) {

		MGraph m = new MGraph();

		VisitedVertex vv = new VisitedVertex(m.verx);

		djs(m, vv, 6);

		System.out.println(Arrays.toString(vv.dis));

	}

	/**
	 * 
	 * @param m
	 *            村庄图
	 * @param vv
	 *            村庄之间连接信息
	 * @param n
	 *            各个村庄到这个节点的距离
	 */
	public static void djs(MGraph m, VisitedVertex vv, int n) {

		List<Integer> list = new ArrayList<>();

		list.add(n);

		// 迪杰斯特拉算法用的是 图的广度优先遍历 所有按照放入list的顺序遍历
		while (!list.isEmpty()) {

			Integer remove = list.remove(0);

			// 标记为已访问
			vv.already_arr[remove] = 1;

			for (int i = 0; i < m.weight.length; i++) {

				// 相连的村庄并且该村庄没有被添加过
				if (m.weight[remove][i] > 0 && vv.already_arr[i] == 0) {

					list.add(i);

					// 如果距离没有被赋值过 或者之前并非最小值
					if (vv.dis[i] == 0 || vv.dis[i] > m.weight[remove][i] + vv.dis[remove]) {
						vv.dis[i] = m.weight[remove][i] + vv.dis[remove];
					}
				}

			}

		}

	}

}

// 记录访问过的节点的信息
class VisitedVertex {

	// 标记 到达过的村庄 1为访问过 0为未访问
	public int[] already_arr;

	// 记住每个节点的前驱节点
	public int[] pre_visited;

	// 保存从起点 到各个节点的最短距离
	public int[] dis;

	public VisitedVertex(int n) {

		already_arr = new int[n];

		pre_visited = new int[n];

		dis = new int[n];

	}

}

// 村庄图(无向图)
class MGraph {

	int verx;

	char[] datas = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };

	int[][] weight;

	public MGraph() {

		// 初始化
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
