package com.tp.controller.Account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.controller.Controller;
import com.tp.controller.HttpUtil;
import com.tp.service.AccountService;
import com.tp.vo.Account;

public class AccountDeleteController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		//service ��ü�� �޼ҵ� ȣ��
		AccountService service = AccountService.getInstance();
		service.AccountDelete(id);
		
		//output view �������� �̵�
		HttpUtil.forward(req, resp, "/result/memberDeleteOutput.jsp");
		
	}

}
