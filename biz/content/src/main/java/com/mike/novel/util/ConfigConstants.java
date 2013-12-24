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

}
