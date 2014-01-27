package com.mike.novel.spider.baidu;

import java.util.ArrayList;
import java.util.List;

public class BaiduDataHolder {

	private List<String> hots = new ArrayList<String>();

	public void add(Object itemIndex, Object order, Object name, Object hotIndex) {
		String oneEntry = itemIndex.toString() + "," + order.toString() + ","
				+ name.toString() + "," + hotIndex.toString() + " \n";
		hots.add(oneEntry);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String hot : hots) {
			sb.append(hot);
		}
		return sb.toString();
	}
}
