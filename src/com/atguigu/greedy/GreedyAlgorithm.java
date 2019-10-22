package com.atguigu.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 贪心算法 解决 电视台覆盖问题 思路： 选取最优解 一直找包含最多未覆盖城市的电台
 * 
 * @author YGQ
 *
 */
public class GreedyAlgorithm {

	// 存储 电视台 及其能覆盖的地区
	static HashMap<String, List<String>> map;

	// 保存需要被覆盖的地区
	static HashSet<String> set;

	public static void main(String[] args) {

		// 添加数据
		map = new HashMap<>();

		List<String> list1 = new ArrayList<>();

		list1.add("北京");
		list1.add("上海");
		list1.add("天津");

		map.put("k1", list1);

		List<String> list2 = new ArrayList<>();

		list2.add("广州");
		list2.add("北京");
		list2.add("深圳");

		map.put("k2", list2);

		List<String> list3 = new ArrayList<>();

		list3.add("成都");
		list3.add("上海");
		list3.add("杭州");

		map.put("k3", list3);

		List<String> list4 = new ArrayList<>();

		list4.add("上海");
		list4.add("天津");

		map.put("k4", list4);

		List<String> list5 = new ArrayList<>();

		list5.add("杭州");
		list5.add("大连");

		map.put("k5", list5);

		set = new HashSet<>();

		set.add("北京");
		set.add("上海");
		set.add("天津");
		set.add("广州");
		set.add("深圳");
		set.add("成都");
		set.add("杭州");
		set.add("大连");

		List<String> list = greedy();

		System.out.println(list);

	}

	public static List<String> greedy() {

		List<String> list = new ArrayList<>();

		// 保存包含未覆盖城市最多的电台
		String maxKey = "";

		// 电台包含的未覆盖城市的数量
		int max = 0;

		// 只要还有未覆盖的城市就一直循环
		while (set.size() > 0) {

			Set<String> keys = map.keySet();

			// 循环所有电台 找到包含未覆盖区域最多的电台
			for (String key : keys) {

				List<String> citys = map.get(key);

				int count = 0;

				for (String city : citys) {

					if (set.contains(city)) {
						count++;
					}

				}

				if (max == 0 || max < count) {
					max = count;
					maxKey = key;
				}

			}

			List<String> maxList = map.get(maxKey);

			list.add(maxKey);

			// 去掉 此次加入电台锁覆盖的区域
			set.removeAll(maxList);

			// 初始化值
			maxKey = "";
			max = 0;

		}

		return list;

	}

}
