package com.mike.novel.util;

public enum NovelType {

	XIUZHENG(1, "修真小说"), xuanhuan(2, "玄幻小说"), dushi(3, "都市小说"), chuanyue(4,
			"穿越小说"), wangyou(5, "网游小说"), kehuan(6, "科幻小说");

	public int type;
	public String name;

	private NovelType(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public static NovelType getType(String name) {
		for (NovelType one : NovelType.values()) {
			if (name.equals(one.name.trim())) {
				return one;
			}
		}
		return null;
	}

	public static String getName(int type) {
		for (NovelType one : NovelType.values()) {
			if (type == one.type) {
				return one.name;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(NovelType.getType("修真小说"));
	}
}
