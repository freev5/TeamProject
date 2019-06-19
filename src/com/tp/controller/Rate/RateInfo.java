package com.tp.controller.Rate;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.controller.Controller;
import com.tp.service.RateService;
import com.tp.vo.Rate;

public class RateInfo implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		ServletContext sc = req.getServletContext();
		
		String Mtitle = req.getParameter("Mtitle");
		String id = (String) ss.getAttribute("Loginid");
		String Rating = req.getParameter("rating");
		String Mcode = req.getParameter("Mcode");
		//String path = "/SearchPage.jsp";
		
		RateService service = RateService.getInstance();
		service.RateInsert(id, Mtitle, Rating, Mcode);
		
		ArrayList<Rate> list = service.RateList(id);
		
		ss.setAttribute("rlist", list);
		//output view 페이지로 이동
		sc.setAttribute("Mtitle", Mtitle);
		
		//HttpUtil.forward(req, resp, path);
		resp.sendRedirect(req.getHeader("referer"));
	}

}
