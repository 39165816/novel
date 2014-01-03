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
					"/home/mike/data/githome/novel/biz/content/src/main/resources/harvest/execute-curl.xml");
		} catch (FileNotFoundException e) {
			throw new RuntimeException("找不到 execute harvest配置文件路径.", e);
		}
		Scraper pageScraper = new Scraper(pageConfig, "/tmp");
		pageScraper.getContext().setVar("taskurl", taskurl);
		pageScraper.execute();
		Variable content = (Variable) pageScraper.getContext().get("content");
		StringBuffer curlResult = new StringBuffer(new String(
				content.toBinary("iso-8859-1")));
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
		String[] result = new String[] {
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1142",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1143",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1144",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1145",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1146",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1147",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1148",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1149",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1150",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1151",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1152",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1153",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1154",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1155",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1156",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1157",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1158",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1159",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1160",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1161",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1162",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1163",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1164",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1165",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1166",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1167",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1168",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1169",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1170",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1171",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1172",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1173",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1174",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1175",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1176",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1177",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1178",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1179",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1180",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1181",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1182",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1183",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1184",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1185",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1186",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1187",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1188",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1189",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1190",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1191",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1192",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1193",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1194",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1195",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1196",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1197",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1198",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1199",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1200",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1201",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1202",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1203",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1204",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1205",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1206",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1207",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1208",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1209",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1210",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1211",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1212",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1213",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1214",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1215",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1216",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1217",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1218",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1219",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1220",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1221",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1222",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1223",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1224",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1225",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1226",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1227",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1228",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1229",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1230",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1231",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1232",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1233",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1234",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1235",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1236",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1237",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1238",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1239",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1240",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1241",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1242",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1243",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1244",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1245",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1246",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1247",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1248",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1249",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1250",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1251",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1252",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1253",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1254",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1255",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1256",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1257",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1258",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1259",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1260",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1261",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1262",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1263",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1264",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1265",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1266",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1267",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1268",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1269",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1270",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1271",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1272",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1273",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1274",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1275",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1276",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1277",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1278",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1279",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1280",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1281",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1282",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1283",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1284",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1285",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1286",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1287",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1288",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1289",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1290",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1291",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1292",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1293",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1294",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1295",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1296",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1297",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1298",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1299",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1300",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1301",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1302",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1303",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1304",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1305",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1306",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1307",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1308",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1309",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1310",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1311",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1312",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1313",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1314",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1315",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1316",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1317",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1318",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1319",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1320",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1321",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1322",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1323",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1324",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1325",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1326",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1327",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1328",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1329",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1330",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1331",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1332",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1333",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1334",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1335",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1336",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1337",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1338",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1339",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1340",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1341",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1342",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1343",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1344",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1345",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1346",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1347",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1348",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1351",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1352",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1353",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1354",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1355",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1356",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1357",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1358",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1359",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1360",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1361",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1362",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1363",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1364",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1365",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1366",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1367",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1368",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1369",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1370",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1371",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1372",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1373",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1374",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1375",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1376",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1377",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1378",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1379",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1380",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1381",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1382",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1383",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1384",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1385",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1386",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1387",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1388",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1389",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1390",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1391",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1392",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1393",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1394",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1395",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1396",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1397",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1398",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1399",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1400",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1401",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1402",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1403",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1404",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1405",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1406",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1407",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1408",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1409",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1410",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1411",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1412",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1413",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1414",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1415",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1416",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1417",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1418",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1419",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1420",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1421",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1422",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1423",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1424",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1425",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1426",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1427",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1428",
				"http://10.72.16.157:8088/content/backdoor/runtask.htm?nid=1429", };

		return result;
	}
}
