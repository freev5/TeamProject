package com.tp.controller.Wish;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.controller.Controller;
import com.tp.controller.HttpUtil;
import com.tp.service.AccountService;
import com.tp.service.WishListService;
import com.tp.vo.Account;
import com.tp.vo.WishList;

public class WishSearchController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String job = req.getParameter("job");
		String path = null;
		
		if(job.equals("Search")) {
			path="/AccountSearch.jsp";
		}
		else if (job.equals("Update")) {
			path="/AccountUpdate.jsp";
		}
		else if (job.equals("Delete")) {
			path="/AccountDelete.jsp";
		}
		
		//��ȿ�� üũ
		if(id.isEmpty()) {
			req.setAttribute("error", "ID�� �Է����ֽñ� �ٶ��ϴ�!");
			HttpUtil.forward(req, resp, path);
			return;
		}
		
		//service ��ü�� �޼ҵ� ȣ��
		WishListService service = WishListService.getInstance();
    	WishList wish = service.WishSearch(id);
		
		//output view �������� �̵�
		if(wish == null) {
			req.setAttribute("result", "�˻��� ������ �����ϴ�!");
		}
		req.setAttribute("WishList", wish);
		if(job.equals("search")) {
			path="/result/AccountSearchOutput.jsp";
		}
		HttpUtil.forward(req, resp, path);
		
	}

}
