package com.tp.controller.Rate;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.controller.Controller;
import com.tp.service.RateService;
import com.tp.vo.Rate;

public class RateListController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		String id = (String)ss.getAttribute("Loginid");
		RateService service = RateService.getInstance();
		ArrayList<Rate> list = service.RateList(id);
		
		ss.setAttribute("rlist", list);
		
		resp.sendRedirect(req.getHeader("referer"));
		//HttpUtil.forward(req, resp, "/result/RateListOutput.jsp");
	}
}
