package com.neu.smty.utils;

import java.math.RoundingMode;

import cn.hutool.core.util.RandomUtil;

public class LocationUtil {
	/**
	 * 获取当前距离
	 * 
	 * @return
	 */
	public static Double getDistance() {
		return RandomUtil.randomDouble(0.5, 10, 1, RoundingMode.HALF_UP);
	}
}
