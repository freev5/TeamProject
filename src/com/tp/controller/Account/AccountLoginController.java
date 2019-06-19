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
import com.tp.service.MDService;
import com.tp.service.RateService;
import com.tp.service.WishListService;
import com.tp.vo.Account;
import com.tp.vo.MD;
import com.tp.vo.Rate;
import com.tp.vo.WishList;

public class AccountLoginController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String path = null;
		path="/Login.jsp";
		//��ȿ�� üũ

		
		//service ��ü�� �޼ҵ� ȣ��
		AccountService service = AccountService.getInstance();
		Account account = service.AccountSearch(id);
		
		
		
		if(account == null) {
			req.setAttribute("result", "���̵� �����ϴ�!");
			HttpUtil.forward(req, resp, path);
			return;
		}
		
		if(id.equals(account.getId())&&pwd.equals(account.getPwd())) {
			req.setAttribute("result", "�α��� ����!");
			req.setAttribute("account", account);
			ss.setAttribute("account", account);
			ss.setAttribute("Loginid", id);
			ss.setAttribute("Loginname",account.getName());
			ss.setAttribute("Loging", "true");
			ss.setAttribute("isAdmin", null);
			if(Integer.parseInt(account.getAid())==1) {
				ss.setAttribute("isAdmin", "true");
			}
			path="/Main.jsp";
			
			RateService rservice = RateService.getInstance();
			ArrayList<Rate> rate = rservice.RateList(id);
			ss.setAttribute("rlist", rate);
			
			WishListService wservice = WishListService.getInstance();
			ArrayList<WishList> wish = wservice.WishList(id);
			ss.setAttribute("wlist", wish);
			
			MDService mservice = MDService.getInstance();
			ArrayList<MD> md = mservice.MDList();
			ss.setAttribute("mlist", md);
			
			AccountService aservice = AccountService.getInstance();
			ArrayList<Account> account1 = aservice.AccountList();
			ss.setAttribute("AccountList", account1);
		}
		else {
			req.setAttribute("result", "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		}
		
		//output view �������� �̵�
		
		
		
		HttpUtil.forward(req, resp, path);
		
	}

}
