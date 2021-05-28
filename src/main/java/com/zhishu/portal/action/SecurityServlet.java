package com.zhishu.portal.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityServlet extends HttpServlet implements Filter {

	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SecurityServlet.class);
	private static final long serialVersionUID = 1L;
	/** 白名单_URL */
	private static String[] WHITE_LIST;
	/** 白名单_资源前缀 */
	private static String[] RESOURCES_PREFIX;
	/** 白名单_资源后缀 */
	private static String[] RESOURCES_SUFFIX;

	@Override
	public void doFilter(ServletRequest serRequest, ServletResponse serRespone, FilterChain filter)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) serRequest;
		HttpServletResponse response = (HttpServletResponse) serRespone;
		HttpSession session = request.getSession(true);

		String uri = request.getRequestURI();
		String ctx = request.getContextPath();

		if (ctx != null && !ctx.equals("") && !ctx.equals("/")) {
			uri = uri.replaceAll(ctx, "");
		}
		// logger.info(uri+"$$$$$$$$$$$");
		String[] indexs = uri.split("/");

		try {

			filter.doFilter(serRequest, serRespone);

		} catch (Exception e) {
			logger.error("", e);
		}

		return;
	}

	private boolean contains(String url) {
		boolean flag = false;
		for (String s : WHITE_LIST) {
			if (s.equals(url)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private boolean containsPrefix(String url) {
		boolean flag = false;
		for (String s : RESOURCES_PREFIX) {
			if (url.startsWith(url + s)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private boolean containsSuffix(String url) {
		boolean flag = false;
		for (String s : RESOURCES_SUFFIX) {
			if (url.endsWith(s)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		WHITE_LIST = config.getInitParameter("WHITE_LIST").split(",");
		/** 前缀 */
		RESOURCES_PREFIX = config.getInitParameter("RESOURCES_PREFIX").split(",");
		/** 后缀 */
		RESOURCES_SUFFIX = config.getInitParameter("RESOURCES_SUFFIX").split(",");
		/** 管理员角色 */

	}
}
