package com.tp.controller.Wish;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.controller.Controller;
import com.tp.service.WishListService;
import com.tp.vo.WishList;

public class WishListController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		String id = (String)ss.getAttribute("Loginid");
		WishListService service = WishListService.getInstance();
		ArrayList<WishList> list = service.WishList(id);
		
		ss.setAttribute("wlist", list);
		
		resp.sendRedirect(req.getHeader("referer"));
		//HttpUtil.forward(req, resp, "/result/RateListOutput.jsp");
	}
}
