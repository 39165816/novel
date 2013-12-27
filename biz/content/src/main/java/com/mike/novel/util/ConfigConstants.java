package com.mike.novel.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 一些基于配置的系统
 */
public class ConfigConstants {

	private Map<String, String> configs = new HashMap<String, String>();

	private static String PICTURE_SAVE_PATH_KEY = "picture_save_path_key";

	private static String PROJECT_BASE_HOME_KEY = "project_base_home_key";

	private static String PICTURE_ACCESS_PATH_KEY = "picture_access_path_key";

	public ConfigConstants() {
	}

	public String getValue(String key) {
		return configs.get(key);
	}

	public void setConfigs(Map<String, String> configs) {
		this.configs = configs;
	}

	public String getPictureSavePath() {
		return getValue(PICTURE_SAVE_PATH_KEY);
	}

	public String getProjectBaseHome() {
		return getValue(PROJECT_BASE_HOME_KEY);
	}

	public String getPictureAccessPath() {
		return getValue(PICTURE_ACCESS_PATH_KEY);
	}

	private Map<String, Integer> recommends = new HashMap<String, Integer>();

	/**
	 * 获得频道pid
	 */
	public Integer getPid(String key) {
		return recommends.get(key);
	}

	public void setRecommends(Map<String, Integer> recommends) {
		this.recommends = recommends;
	}

}
