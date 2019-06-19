package com.tp.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.tp.controller.Account.*;
import com.tp.controller.MD.*;
import com.tp.controller.Rate.*;
import com.tp.controller.Wish.*;

import java.util.*;

public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	String charset = null;
	HashMap<String, Controller> list = null;
	
	@Override
	public void init(ServletConfig sc) throws ServletException {
		charset = sc.getInitParameter("charset");
		list = new HashMap<String, Controller>();
		list.put("/AccountRegist.do", new AccountRegistController());
		list.put("/AccountSearch.do", new AccountSearchController());
		list.put("/AccountUpdate.do", new AccountUpdateController());
		list.put("/AccountDelete.do", new AccountDeleteController());
		list.put("/AccountLogin.do", new AccountLoginController());
		list.put("/AccountLogout.do", new AccountLogoutController());
		list.put("/AccountList.do", new AccountListController());
		
		list.put("/MDList.do", new MDListController());
		list.put("/MDDelete.do", new MDDeleteController());
		
		list.put("/RateInsert.do", new RateInsertController());
		list.put("/RateUpdate.do", new RateUpdateController());
		list.put("/RateList.do", new RateListController());
		
		
		list.put("/WishInsert.do", new WishInsertController());
		list.put("/WishList.do", new WishListController());
		list.put("/WishSearch.do", new WishSearchController());
		list.put("/WishDelete.do", new WishDeleteController());
		
		list.put("/MovieSearch.do", new MovieSearchController());
		
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = url.substring(contextPath.length());
		Controller subController = list.get(path);
		subController.execute(req, resp);
	}

}
