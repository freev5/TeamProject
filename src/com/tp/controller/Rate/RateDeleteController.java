package com.tp.controller.Rate;

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

public class RateDeleteController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		//String id = (String) ss.getAttribute("LoginId");
		String id = (String) ss.getAttribute("Loginid");
		String Mtitle = req.getParameter("Mtitle");
		
		//service 객체의 메소드 호출
		RateService service = RateService.getInstance();
		service.RateDelete(id,Mtitle);
		
		ArrayList<Rate> rate = service.RateList(id);
		ss.setAttribute("rlist", rate);

		WishListService wservice = WishListService.getInstance();
		ArrayList<WishList> wish = wservice.WishList(id);
		ss.setAttribute("wlist", wish);
		//output view 페이지로 이동
		//HttpUtil.forward(req, resp, "/result/memberDeleteOutput.jsp");
		resp.sendRedirect(req.getHeader("referer"));
		
	}

}
