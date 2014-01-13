package com.tmall.neo.home.web.module.screen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.util.ConfigConstants;

/**
 * �����ļ�
 * 
 */
public class DownloadTxtFile {
	@Autowired
	private BufferedRequestContext buffered;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private NovelBasicService novelBasicService;

	@Resource
	private ConfigConstants configConstants;

	private static Logger log = LoggerFactory.getLogger(DownloadTxtFile.class);

	private static final String ENCODING = "UTF-8";
	// ͬʱ���������û�����
	private static AtomicInteger allowNum = new AtomicInteger(2);
	// �����ٶ�50k
	private static final int MAX_SPEED = 50 * 1024;

	public void execute(@Param("nid") String nid, Context context, Navigator nav) {
		// ������
		if (null == nid || nid.equals("")) {
			context.put("errMsg", "nidΪ��");
			nav.forwardTo("/error.vm");
			return;
		}
		int intNid = Integer.parseInt(nid);

		synchronized (this) {
			if (allowNum.intValue() < 1) {
				// �����ǰ����������
				context.put("errMsg", "�ף���ǰ���ص���̫�࣬���Ժ����ԣ���Ϊ���طǳ����������������վ������Щ���ƣ����Щʱ����г��ԣ�վ����Ʒ��֤��һ�������سɹ��ģ�");
				nav.forwardTo("/error.vm");
				return;
			}
			allowNum.decrementAndGet();
		}

		ServletOutputStream out = null;
		try {
			NovelBasicDo novelBasic = novelBasicService.queryByNid(intNid);
			buffered.setBuffering(false);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + java.net.URLEncoder.encode(novelBasic.getTitle() + ".txt", "UTF-8"));
			out = response.getOutputStream();
			getFileString(novelBasic, out);
			out.flush();
		} catch (IOException e) {
			log.error("execute", e);
		} finally {
			allowNum.incrementAndGet();
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

	}

	private void getFileString(NovelBasicDo novelBasic, ServletOutputStream out) {
		String filePath = configConstants.getProjectBaseHome() + "txt/" + novelBasic.getTitle() + novelBasic.getNid()
				+ ".txt";
		BufferedReader reader = null;
		try {
			File file = new File(filePath);
			// һ�ζ�����ֽ�
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			long startTime = System.currentTimeMillis();
			int length = 0;
			while ((tempString = reader.readLine()) != null) {
				byte[] bytes = tempString.getBytes(ENCODING);
				out.write(bytes);
				out.write("\r\n".getBytes(ENCODING));
				length += bytes.length;
				if (System.currentTimeMillis() - startTime < 1000) {
					if (length >= MAX_SPEED) {
						Thread.sleep(1000 - (System.currentTimeMillis() - startTime));
					}
				} else {// ��������
					startTime = System.currentTimeMillis();
					length = 0;
				}
			}
		} catch (Exception e1) {
			log.error("execute", e1);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	private void getFileBytes(NovelBasicDo novelBasic, ServletOutputStream out) {
		String filePath = configConstants.getProjectBaseHome() + "txt/" + novelBasic.getTitle() + novelBasic.getNid()
				+ ".txt";
		InputStream in = null;
		try {
			File file = new File(filePath);
			// һ�ζ�����ֽ�
			byte[] tempbytes = new byte[1000];
			int byteread = 0;
			in = new FileInputStream(file);
			// �������ֽڵ��ֽ������У�bytereadΪһ�ζ�����ֽ���
			while ((byteread = in.read(tempbytes)) != -1) {
				out.write(tempbytes, 0, byteread);
			}
		} catch (Exception e1) {
			log.error("execute", e1);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(java.net.URLEncoder.encode("������.url", "UTF-8"));
	}
}
