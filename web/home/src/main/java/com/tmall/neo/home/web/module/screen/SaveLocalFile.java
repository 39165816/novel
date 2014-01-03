package com.tmall.neo.home.web.module.screen;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;

/**
 * 保存到本地
 * 
 */
public class SaveLocalFile {
	@Autowired
	private BufferedRequestContext buffered;

	@Autowired
	private HttpServletResponse response;

	/**
	 * 本地文件中的内容
	 * 
	 */
	private static final String urlContent = "[InternetShortcut]\n"
			+ "URL=http://www.shuzhongshu.com/\n"
			+ "IconFile=C:\\WINDOWS\\system32\\SHELL32.dll\n"
			+ "IconIndex=43\n" + "IDList=\n"
			+ "[{000214A0-0000-0000-C000-000000000046}]\n" + "Prop3=19,2\n";

	public void execute(Context context, Navigator nav) {
		buffered.setBuffering(false);
		response.setContentType("application/octet-stream");
		try {
			response.setHeader("Content-Disposition", "attachment; filename="
					+ java.net.URLEncoder.encode("书中书.url", "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
		}

		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(urlContent.getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(java.net.URLEncoder.encode("书中书.url", "UTF-8"));
	}
}
