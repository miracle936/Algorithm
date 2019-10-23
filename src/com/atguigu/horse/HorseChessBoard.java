package com.atguigu.horse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HorseChessBoard {

	// 棋盘的大小
	private static int counts = 6;

	private static boolean flag = false;

	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		int[][] chess = new int[counts][counts];

		horse(chess, 0, 0, 1);

		long end = System.currentTimeMillis();

		System.out.println("一共用时:" + (end - start));

		for (int i = 0; i < chess.length; i++) {
			System.out.println(Arrays.toString(chess[i]));
		}

	}

	/**
	 * 马踏棋盘的主要算法
	 * 
	 * @param chess
	 *            棋盘
	 * @param x
	 *            横坐标
	 * @param y
	 *            纵坐标
	 * @param step
	 *            当前的步数
	 */
	public static void horse(int[][] chess, int x, int y, int step) {

		chess[x][y] = step;

		List<Point> next = next(new Point(x, y));

		// 贪心算法优化
		sort(next);

		while (!next.isEmpty()) {

			Point point = next.remove(0);

			// 判断点走没走过
			if (chess[point.x][point.y] == 0) {
				horse(chess, point.x, point.y, step + 1);
			}
		}

		if (step == counts * counts || flag) {
			flag = true;
		} else {
			// 如果这个走法不成立 则将走错的步复原
			chess[x][y] = 0;
		}

	}

	/**
	 * 获得马接下来可以走的所有可能性
	 * 
	 * @param current
	 *            马当前所在的位置
	 * @return
	 */
	public static List<Point> next(Point current) {

		List<Point> list = new ArrayList<>();

		if (current.x - 1 >= 0 && current.y - 2 >= 0) {
			list.add(new Point(current.x - 1, current.y - 2));
		}

		if (current.x - 2 >= 0 && current.y - 1 >= 0) {
			list.add(new Point(current.x - 2, current.y - 1));
		}

		if (current.x - 1 >= 0 && current.y + 2 < counts) {
			list.add(new Point(current.x - 1, current.y + 2));
		}

		if (current.x - 2 >= 0 && current.y + 1 < counts) {
			list.add(new Point(current.x - 2, current.y + 1));
		}

		if (current.x + 1 < counts && current.y + 2 < counts) {
			list.add(new Point(current.x + 1, current.y + 2));
		}

		if (current.x + 2 < counts && current.y + 1 < counts) {
			list.add(new Point(current.x + 2, current.y + 1));
		}

		if (current.x + 1 < counts && current.y - 2 >= 0) {
			list.add(new Point(current.x + 1, current.y - 2));
		}

		if (current.x + 2 < counts && current.y - 1 >= 0) {
			list.add(new Point(current.x + 2, current.y - 1));
		}

		return list;
	}

	// 贪心算法：将马下一步的下一步的走法数算出来从下到大排序 这样可以节约时间
	public static void sort(List<Point> list) {

		list.sort(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {

				int count1 = next(o1).size();
				int count2 = next(o2).size();

				return count1 - count2;
			}

		});

	}

}

// 棋盘上的点
class Point {

	public int x;

	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

}