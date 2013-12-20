package com.mike.novel.spider.biqege;

import java.util.ArrayList;
import java.util.List;

import com.mike.novel.dto.NovelChapterDo;
import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.dto.TasksDo;

public abstract class BggIndexParseHelper {

	private static String volumStartFlag = "<dt>";
	private static String volumEndFlag = "</dt>";
	private static String chapterStartFlag = "<dd>";
	private static String chapterEndFlag = "</dd>";

	/**
	 * @param original
	 *            原始内容
	 * @param volums
	 *            空的卷信息，用来储存结果
	 * @param tasks
	 *            空的任务信息，用来储存结果
	 * @param nameLength
	 *            书名的长度，用来过滤卷中的无用书名
	 */
	public static void parse(String original, List<NovelVolumDo> volums, int nameLength) {
		// 跳过第一个无用卷
		int currentPosition = original.indexOf(volumEndFlag);
		currentPosition = original.indexOf(volumStartFlag, currentPosition);
		if (currentPosition == -1) {
			return;
		}

		// 通过currentPosition的变化，不断推进
		NovelVolumDo currentVolum = null;
		int vnum = 1;
		int cnum = 1;
		int copy = currentPosition;
		while (true) {
//			System.out.println("currentPosition"+currentPosition);
			if (copy > currentPosition){
				System.err.println("something goes wrong");
			}
			copy = currentPosition;
			int status = isVolum(currentPosition, original);
			if (status == 1) {// 卷信息
				currentVolum = new NovelVolumDo();
				currentVolum.setVnum(vnum++);
				volums.add(currentVolum);
				currentPosition = parseVname(original, nameLength,
						currentPosition, currentVolum);
			} else if (status == 2) {// 章信息
				currentPosition = parseChapter(original, currentVolum,
						currentPosition, cnum);
				cnum++;
			} else if (status == 0) {// 结束了
				return;
			}
		}
	}

	private static int isVolum(int currentPosition, String original) {
		int volumPosition = original.indexOf(volumStartFlag, currentPosition);
		int chapterPosition = original.indexOf(chapterStartFlag,
				currentPosition);

		if (volumPosition == -1 && chapterPosition == -1) {
			return 0;
		}
		
		if (volumPosition==-1){//卷已结束
			return 2;
		}

		if (volumPosition < chapterPosition) {
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 * 范例： + "</dd>\n" + "<dt>《独步天下》第一卷 武道先天</dt>\n" + "<dd>\n"
	 */
	private static int parseVname(String original, int nameLength,
			int currentPosition, NovelVolumDo currentVolum) {
		int startPoint = original.indexOf(volumStartFlag, currentPosition
				) + volumStartFlag.length();
		int endPoint = original.indexOf(volumEndFlag, startPoint);
		String vname = original
				.substring(startPoint + nameLength + 2, endPoint);
		currentVolum.setVname(vname);
		return endPoint + volumEndFlag.length();
	}

	/**
	 * 范例：// "<dd>\n" // + " <a href=\"/0_494/205885.html\">第一章 武道先天</a>\n" + "</dd>
	 */
	private static int parseChapter(String original, NovelVolumDo currentVolum,
			int currentPosition, int cnum) {
		NovelChapterDo novelChapterDo = new NovelChapterDo();
		novelChapterDo.setCnum(cnum);

		// 分析url
		int chapterPosition = original.indexOf(chapterStartFlag,
				currentPosition);
		int urlStartPosition = original.indexOf("\"", chapterPosition
				+ chapterStartFlag.length());
		int urlEndPosition = original.indexOf("\"", urlStartPosition + 1);
		String url = BqgConstants.BQG_WEBSITE
				+ original.substring(urlStartPosition+1, urlEndPosition);
		TasksDo oneTask = new TasksDo();
		oneTask.setUrl(url);
		novelChapterDo.setTask(oneTask);

		// 分析章名
		int cnameStartPosition = original.indexOf(">", urlEndPosition) + 1;
		int cnameEndPosition = original.indexOf("<", cnameStartPosition) ;
		String cname = original.substring(cnameStartPosition, cnameEndPosition);
		novelChapterDo.setCname(cname);
//		System.out.println(cname);
		
		List<NovelChapterDo> chapters = currentVolum.getChapters();
		if (chapters ==null){
			chapters = new ArrayList<NovelChapterDo>();
			currentVolum.setChapters(chapters);
		}
		chapters.add(novelChapterDo);

		return original.indexOf(chapterEndFlag, cnameEndPosition)
				+ chapterEndFlag.length();
	}
}
