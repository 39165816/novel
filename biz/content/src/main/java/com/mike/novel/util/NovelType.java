package com.mike.novel.util;

public enum NovelType {

	XIUZHENG(1, "����С˵"), xuanhuan(2, "����С˵"), dushi(3, "����С˵"), chuanyue(4,
			"��ԽС˵"), wangyou(5, "����С˵"), kehuan(6, "�ƻ�С˵");

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
		System.out.println(NovelType.getType("����С˵"));
	}
}
