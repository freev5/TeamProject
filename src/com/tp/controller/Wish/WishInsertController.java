package com.tp.controller.Wish;

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

public class WishInsertController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		ServletContext sc = req.getServletContext();
		String id = (String) ss.getAttribute("Loginid");
		
		String Mtitle = req.getParameter("Mtitle");
		String Mcode = req.getParameter("Mcode");
		String Loginid = (String) ss.getAttribute("Loginid");
		
		String path = "/SearchPage.jsp";
		
		WishListService service = WishListService.getInstance();
		service.WishInsert(Loginid, Mtitle, Mcode);
		
    	ArrayList<WishList> wish = service.WishList(id);
		ss.setAttribute("wlist", wish);
		RateService rservice = RateService.getInstance();
		ArrayList<Rate> rate = rservice.RateList(id);
		ss.setAttribute("rlist", rate);
		//output view 페이지로 이동
		req.setAttribute("Mtitle", Mtitle);
		sc.setAttribute("Mtitle", Mtitle);
		
		//HttpUtil.forward(req, resp, path);
		resp.sendRedirect(req.getHeader("referer"));
	}

}
