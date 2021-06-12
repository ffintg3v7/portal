package com.zhishu.portal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/action/portal")
public class PortalAction {
	private static Logger logger = Logger.getLogger(PortalAction.class);

	@ResponseBody
	@RequestMapping(value = "/submiturl")
	public ModelAndView submitUrl(HttpServletRequest request, HttpServletResponse response, String website)
			throws Exception {
		String sid = request.getSession().getId();

		ModelAndView ret = new ModelAndView();
		try {
			ret.setViewName("/view/process.jsp");
			logger.info(sid);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return ret;
	}
}
