package com.mike.novel.tools;

import java.io.FileNotFoundException;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

public class ExecueTask {

	public static void accessChapter(String taskurl) {
		ScraperConfiguration pageConfig;
		try {
			pageConfig = new ScraperConfiguration(
					"/Users/mike/code/github/novel/biz/content/src/main/resources/harvest/execute-curl.xml");
		} catch (FileNotFoundException e) {
			throw new RuntimeException("找不到 execute harvest配置文件路径.", e);
		}
		Scraper pageScraper = new Scraper(pageConfig, "/tmp");
		pageScraper.getContext().setVar("taskurl", taskurl);
		pageScraper.execute();
		Variable content = (Variable) pageScraper.getContext().get("content");
		StringBuffer curlResult = new StringBuffer(new String(content.toBinary("iso-8859-1")));
		// System.out.println("curlResult=" + curlResult);
		System.err.println("one task is finished. taskurl" + taskurl);
	}

	public static void main(String[] args) throws InterruptedException {
		for (String oneTask : getTarget()) {
			accessChapter(oneTask);
			Thread.sleep(90 * 1000);
		}

	}

	// public static String[] getTarget() {
	// String[] result = new String[] {
	// "http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1082", };
	//
	// return result;
	// }

	public static String[] getTarget() {
		String[] result = new String[] { "http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1061",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1062",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1063",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1064",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1065",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1066",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1067",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1068",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1069",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1070",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1071",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1072",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1081",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1083",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1084",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1085",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1086",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1091",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1087",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1088",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1089",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1090",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1101",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1102",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1103",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1104",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1105",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1106",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1107",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1108",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1109",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1110",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1111",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1112",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1113",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1114",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1115",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1116",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1117",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1118",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1119",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1120",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1121",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1122",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1123",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1124",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1125",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1126",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1127",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1128",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1129",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1130",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1131",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1132",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1133",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1134", };

		return result;
	}
}
