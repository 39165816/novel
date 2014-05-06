package com.mike.novel.spider.baidu;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

import com.mike.novel.util.BqgConstants;
import com.mike.novel.util.ConfigConstants;

/**
 * ��ȥ�ٶȵ�����top 50��С˵ http://top.baidu.com/buzz?b=7&c=10
 * 
 * @author mike
 */
public class BaiduHotAccess {

    private final static String BAIDU_TOP_URL = "http://top.baidu.com/buzz?b=7&c=10";
    @Resource
    private ConfigConstants     configConstants;

    private static boolean      isFromMain    = false;

    public String accessTop50() throws UnsupportedEncodingException {
        ScraperConfiguration pageConfig;
        try {
            pageConfig = new ScraperConfiguration(configConstants.getConfigFilePath()
                    + BqgConstants.BAIDU_TOP_FILE_NAME);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("�Ҳ����ٶ�topС˵�� �����ļ�·��.", e);
        }
        Scraper pageScraper = new Scraper(pageConfig, getProjectBaseHome()
                + BqgConstants.HARVEST_WORKING_DIR);
        pageScraper.getContext().setVar("topUrl", BAIDU_TOP_URL);
        pageScraper.execute();
        Variable baiduDataHolder = (Variable) pageScraper.getContext().get("baiduDataHolder");

        // System.out.println("baiduDataHolder.getWrappedObject().toString()"
        // + baiduDataHolder.getWrappedObject().toString());
        // System.out.println("default" + new
        // String(baiduDataHolder.toBinary()));

        return filterUsedInfo(baiduDataHolder.getWrappedObject().toString());
    }

    private String getProjectBaseHome() {
        if (isFromMain) {
            return "/home/mike/data/githome/novel/";
        } else {
            return configConstants.getProjectBaseHome();
        }
    }

    private String filterUsedInfo(String originl) {
        String[] split = originl.split("\n");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6) {
                continue;
            }
            sb.append(split[i]).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String result = new BaiduHotAccess().accessTop50();
        System.out.println("result = " + result);
    }

}
