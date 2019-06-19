package com.tp.controller.Account;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.controller.Controller;
import com.tp.controller.HttpUtil;
import com.tp.service.AccountService;
import com.tp.vo.Account;

public class AccountListController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccountService service = AccountService.getInstance();
		ArrayList<Account> list = service.AccountList();
		
		req.setAttribute("list", list);
		
		HttpUtil.forward(req, resp, "/result/AccountListOutput.jsp");
	}
}
