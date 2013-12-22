package com.mike.novel.util;

public abstract class PictureUrlGene {
	// TODO:这个路径可配置
	private static String BASE_PATH = "/Users/mike/pics/";

	public static String getAPictureUrl() {
		StringBuffer sb = new StringBuffer(BASE_PATH);
		sb.append(System.nanoTime());
		sb.append(".jpg");
		return sb.toString();
	}
}
