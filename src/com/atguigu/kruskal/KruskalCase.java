package com.atguigu.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 克鲁斯卡尔算法 思路： 获取所有能直接相连的村庄及他们之间的距离 按照从小到大的排序 只要相连接的两个点的终点不同 那么就可以连接
 * 直到所有的村庄都连接上了
 * 
 * @author YGQ
 *
 */
public class KruskalCase {

	public static void main(String[] args) {

		MGraph m = new MGraph();

		List<EData> list = kruskal(m);

		// 记录总共修路的长度
		int sum = 0;

		for (EData e : list) {
			System.out.println("从" + e.start + "修到" + e.end + "修了:" + e.value);
			sum += e.value;
		}

		System.out.println("一共修了:" + sum);

	}

	public static List<EData> kruskal(MGraph m) {

		List<EData> list = new ArrayList<>();

		// 排序
		Collections.sort(m.list);

		for (EData eData : m.list) {

			// 起点的下标
			int start = m.getIndexByValue(eData.start);

			// 终点的下标
			int end = m.getIndexByValue(eData.end);

			// 起点所连接的终点的坐标
			int destination = m.getEnd(start);

			// 两个连接点的终点坐标不同
			if (destination != m.getEnd(end)) {

				list.add(eData);

				// 改变终点index
				for (int i = 0; i < m.end.length; i++) {

					if (m.end[i] == destination) {
						m.end[i] = m.getEnd(end);
					}

				}

			}

		}

		return list;

	}

}

class MGraph {

	// 节点个数
	int verx;

	// 村庄之间的距离
	int[][] weight;

	// 村庄
	char[] data = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };

	// 保存村庄之间的距离
	List<EData> list = new ArrayList<>();

	// 记录各个节点终点的下标
	int[] end;

	public MGraph() {

		// 初始化
		verx = data.length;

		weight = new int[data.length][data.length];

		end = new int[data.length];

		// A-B
		weight[0][1] = 12;
		weight[1][0] = 12;

		// A-F
		weight[0][5] = 16;
		weight[5][0] = 16;

		// A-G
		weight[0][6] = 14;
		weight[6][0] = 14;

		// B-F
		weight[1][5] = 7;
		weight[5][1] = 7;

		// G-F
		weight[6][5] = 9;
		weight[5][6] = 9;

		// B-C
		weight[1][2] = 10;
		weight[2][1] = 10;

		// G-E
		weight[6][4] = 8;
		weight[4][6] = 8;

		// F-C
		weight[5][2] = 6;
		weight[2][5] = 6;

		// F-E
		weight[5][4] = 2;
		weight[4][5] = 2;

		// C-E
		weight[2][4] = 5;
		weight[4][2] = 5;

		// C-D
		weight[2][3] = 3;
		weight[3][2] = 3;

		// E-D
		weight[4][3] = 4;
		weight[3][4] = 4;

		// 保存所有能直接相连的村庄和道路的长度
		for (int i = 0; i < data.length; i++) {

			for (int j = i + 1; j < data.length; j++) {

				if (weight[i][j] > 0) {
					list.add(new EData(data[i], data[j], weight[i][j]));
				}

			}
		}

		// 初始化各个节点的终点的index
		for (int i = 0; i < end.length; i++) {
			end[i] = i;
		}

	}

	// 根据值获取下标
	public int getIndexByValue(char value) {

		for (int i = 0; i < data.length; i++) {
			if (data[i] == value) {
				return i;
			}
		}

		return -1;

	}

	// 获取下标为i的节点的终点
	public int getEnd(int i) {

		// end 每个节点的终点index初始化为本身 所以判断条件i != end[i]
		while (end[i] != 0 && i != end[i]) {
			i = end[i];
		}

		return i;

	}

}

// 道路
class EData implements Comparable<EData> {

	// 起始村庄
	char start;

	// 终点村庄
	char end;

	// 道路长度
	int value;

	public EData(char start, char end, int value) {
		super();
		this.start = start;
		this.end = end;
		this.value = value;
	}

	public EData() {
		super();
	}

	@Override
	public int compareTo(EData o) {
		return value - o.value;
	}

	@Override
	public String toString() {
		return "EData [start=" + start + ", end=" + end + ", value=" + value + "]";
	}

}