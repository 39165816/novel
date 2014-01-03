package com.mike.novel.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SiteUtils {

	private final static Logger log = LoggerFactory.getLogger(SiteUtils.class);

	// 将中文转换为拼音
	@SuppressWarnings("deprecation")
	public static String getEname(String name) {
		HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
		pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

		try {
			return PinyinHelper.toHanyuPinyinString(name, pyFormat, "");
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			log.error("getEname", e);
			return "";
		}
	}

	public static void main(String[] args)
			throws BadHanyuPinyinOutputFormatCombination {
		System.out.println(getEname("顾海全"));
	}
}
