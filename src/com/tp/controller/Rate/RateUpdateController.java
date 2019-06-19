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
import com.tp.service.WishListService;
import com.tp.vo.Rate;
import com.tp.vo.WishList;

public class RateUpdateController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		ServletContext sc = req.getServletContext();
		
		String id = (String) req.getSession().getAttribute("Loginid");
		String Mtitle = req.getParameter("Mtitle");
		String Rating = req.getParameter("rating");
		
		
		RateService service = RateService.getInstance();
		//점수가 0이면 삭제
		if(Integer.parseInt(Rating)==0) {service.RateDelete(id, Mtitle);}
		else {service.RateUpdate(id, Mtitle, Rating);}
		
		
		WishListService wservice = WishListService.getInstance();
		ArrayList<WishList> wish = wservice.WishList(id);
		ss.setAttribute("wlist", wish);
		
		
		
		ArrayList<Rate> rate = service.RateList(id);
		ss.setAttribute("rlist", rate);
		//output view 페이지로 이동
		//req.setAttribute("id", id);
		sc.setAttribute("Mtitle", Mtitle);
		resp.sendRedirect(req.getHeader("referer"));
		//HttpUtil.forward(req, resp, "/result/AccountUpdateOutput.jsp");
		
	}

}
