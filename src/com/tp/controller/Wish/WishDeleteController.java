package com.tp.controller.Wish;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.controller.Controller;
import com.tp.service.AccountService;
import com.tp.service.RateService;
import com.tp.service.WishListService;
import com.tp.vo.Account;
import com.tp.vo.Rate;
import com.tp.vo.WishList;

public class WishDeleteController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		//String id = (String) ss.getAttribute("LoginId");
		String id = (String) ss.getAttribute("Loginid");
		String Mtitle = req.getParameter("Mtitle");
		
		//service 객체의 메소드 호출
		WishListService service = WishListService.getInstance();
		service.WishDelete(id,Mtitle);
		
		ArrayList<WishList> wish = service.WishList(id);
		ss.setAttribute("wlist", wish);
		RateService rservice = RateService.getInstance();
		ArrayList<Rate> rate = rservice.RateList(id);
		ss.setAttribute("rlist", rate);
		//output view 페이지로 이동
		//HttpUtil.forward(req, resp, "/result/memberDeleteOutput.jsp");
		resp.sendRedirect(req.getHeader("referer"));
		
	}

}
