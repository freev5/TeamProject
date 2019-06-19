package com.tp.controller.Account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.controller.Controller;
import com.tp.controller.HttpUtil;
import com.tp.service.AccountService;
import com.tp.vo.Account;

public class AccountSearchController implements Controller{
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
		
		//유효성 체크
		if(id.isEmpty()) {
			req.setAttribute("error", "ID를 입력해주시기 바랍니다!");
			HttpUtil.forward(req, resp, path);
			return;
		}
		
		//service 객체의 메소드 호출
		AccountService service = AccountService.getInstance();
		Account account = service.AccountSearch(id);
		
		//output view 페이지로 이동
		if(account == null) {
			req.setAttribute("result", "검색된 정보가 없습니다!");
		}
		req.setAttribute("account", account);
		if(job.equals("search")) {
			path="/result/AccountSearchOutput.jsp";
		}
		HttpUtil.forward(req, resp, path);
		
	}

}
