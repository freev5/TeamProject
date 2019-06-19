package com.tp.controller.Account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.controller.Controller;
import com.tp.controller.HttpUtil;
import com.tp.service.AccountService;
import com.tp.vo.Account;

public class AccountUpdateController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String email = req.getParameter("email");
		
		if(id.isEmpty() || pwd.isEmpty() || name.isEmpty() || email.isEmpty()) {
			req.setAttribute("error", "모든 항목을 빠짐없이 입력해주시기 바랍니다.");
			HttpUtil.forward(req, resp, "/AccountUpdate.jsp");
			return;
		}
		
		Account account = new Account();
		account.setId(id);
		account.setPwd(pwd);
		account.setName(name);
		account.setEmail(email);
		account.setBirth(birth);
		
		AccountService service = AccountService.getInstance();
		service.AccountUpdate(account);
		
		ss.setAttribute("account", account);
		
		//output view 페이지로 이동
		req.setAttribute("id", id);
		resp.sendRedirect(req.getHeader("referer"));
		
	}

}
