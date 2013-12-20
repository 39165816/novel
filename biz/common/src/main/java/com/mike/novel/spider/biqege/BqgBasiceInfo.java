package com.mike.novel.spider.biqege;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.util.NovelType;

/**
 * biquge������Ϣ����
 * 
 * @author ���� 2013��12��18������6:56:46
 */
public class BqgBasiceInfo {

    private ScraperConfiguration indexConfig;
    private Scraper              indexScraper;

    public BqgBasiceInfo() throws FileNotFoundException {
        indexConfig = new ScraperConfiguration(BqgConstants.HARVEST_INDEX_CONFIG);
        indexScraper = new Scraper(indexConfig, BqgConstants.HARVEST_WORKING_DIR);
    }

    public void executeIndexPage(String indexPage) {
        indexScraper.getContext().setVar("IndexPage", indexPage);
        indexScraper.execute();

        NovelBasicDo novelBasicDo = new NovelBasicDo();
        //����
        Variable title = (Variable) indexScraper.getContext().get("title");
        novelBasicDo.setTitle(title.toString());
        //����
        Variable author = (Variable) indexScraper.getContext().get("author");
        System.out.println(author.toString().split("��")[1]);
        novelBasicDo.setAuthor(author.toString().split("��")[1]);
        //������ʱ��
        Variable lastUpdateTime = (Variable) indexScraper.getContext().get("lastUpdateTime");
        System.out.println(lastUpdateTime.toString().split("��")[1]);
        novelBasicDo.setLastUpdateTime(Date.valueOf(lastUpdateTime.toString().split("��")[1]));
        //���
        Variable introduce = (Variable) indexScraper.getContext().get("introduce");
        System.out.println(introduce.toString());
        novelBasicDo.setIntroduce(introduce.toString());
        //ͼƬ��ַ
        Variable pictureUrl = (Variable) indexScraper.getContext().get("pictureUrl");
        System.out.println(BqgConstants.BQG_WEBSITE + pictureUrl.toString());
        //TODO: ����ͼƬ��ַ�ͱ���
        novelBasicDo.setPicturePath(BqgConstants.BQG_WEBSITE + pictureUrl.toString());
        //����
        Variable type = (Variable) indexScraper.getContext().get("type");
        System.out.println(type.toString());
        novelBasicDo.setType(NovelType.getType(type.toString()).type);

        Variable allinfo = (Variable) indexScraper.getContext().get("allinfo");
        System.out.println(allinfo.toString());

    }

    public static void main(String[] args) throws IOException {
        new BqgBasiceInfo().executeIndexPage("http://www.biquge.com/0_494/");
    }
}
