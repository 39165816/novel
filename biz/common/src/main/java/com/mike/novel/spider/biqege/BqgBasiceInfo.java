package com.mike.novel.spider.biqege;

import java.io.FileNotFoundException;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

/**
 * biquge基本信息爬虫
 * 
 * @author 听雷 2013年12月18日下午6:56:46
 */
public class BqgBasiceInfo {

    private ScraperConfiguration indexConfig;
    private ScraperConfiguration pageConfig;
    private Scraper              indexScraper;
    private Scraper              pageScraper;

    public BqgBasiceInfo() throws FileNotFoundException {
        indexConfig = new ScraperConfiguration(BqgConstants.HARVEST_INDEX_CONFIG);
        pageConfig = new ScraperConfiguration(BqgConstants.HARVEST_PAGE_CONFIG);
        indexScraper = new Scraper(indexConfig, BqgConstants.HARVEST_WORKING_DIR);
        pageScraper = new Scraper(pageConfig, BqgConstants.HARVEST_WORKING_DIR);
    }

    public void execute() {
        indexScraper.execute();
        Variable title = (Variable) indexScraper.getContext().get("title");
        System.out.println(title.toString());
    }

    public static void main(String[] args) throws FileNotFoundException {
        new BqgBasiceInfo().execute();
    }
}
