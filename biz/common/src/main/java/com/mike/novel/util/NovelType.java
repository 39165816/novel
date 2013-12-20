package com.mike.novel.util;

public enum NovelType {

    XIUZHENG(1, "修真小说");

    public int    type;
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

    public static void main(String[] args) {
        System.out.println(NovelType.getType("修真小说"));
    }
}
