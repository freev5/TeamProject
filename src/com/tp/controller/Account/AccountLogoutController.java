package com.tp.controller.Account;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.controller.Controller;
import com.tp.controller.HttpUtil;
import com.tp.service.AccountService;
import com.tp.service.RateService;
import com.tp.service.WishListService;
import com.tp.vo.Account;
import com.tp.vo.Rate;
import com.tp.vo.WishList;

public class AccountLogoutController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		String path = null;
			ss.setAttribute("Loginid", "guest");
			ss.setAttribute("Loging", null);
			ss.setAttribute("isAdmin", null);
			path="/Main.jsp";
		//output view 페이지로 이동
		HttpUtil.forward(req, resp, path);
	}
}
