package com.tp.controller.Account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.controller.Controller;
import com.tp.controller.HttpUtil;
import com.tp.service.AccountService;
import com.tp.vo.Account;

public class AccountRegistController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String email = req.getParameter("email");
		
		

		
		if(id.isEmpty() || pwd.isEmpty() || name.isEmpty() || email.isEmpty() || birth.isEmpty()) {
			req.setAttribute("error", "��� �׸��� �������� �Է����ֽñ� �ٶ��ϴ�.");
			HttpUtil.forward(req, resp, "/Register.jsp");
			return;
		}
		
		Account account = new Account();
		account.setId(id);
		account.setPwd(pwd);
		account.setName(name);
		account.setBirth(birth);
		account.setEmail(email);
		
		AccountService service = AccountService.getInstance();
		Account re = service.AccountSearch(id);
		if(re==null) {
		service.AccountRegist(account);
		}
		else {
			req.setAttribute("result", "�ߺ��Ǵ� ���̵� �ֽ��ϴ�.");
		}
		//output view �������� �̵�
		HttpUtil.forward(req, resp, "/Main.jsp");
		
	}

}
