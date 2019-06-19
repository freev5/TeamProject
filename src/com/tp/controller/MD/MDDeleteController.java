package com.tp.controller.MD;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.controller.Controller;
import com.tp.controller.HttpUtil;
import com.tp.service.MDService;
import com.tp.vo.MD;

public class MDDeleteController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		String Mtitle = req.getParameter("Mtitle");
		
		//service 객체의 메소드 호출
		MDService service = MDService.getInstance();
		service.MDDelete(Mtitle);
		
		MDService mservice = MDService.getInstance();
		ArrayList<MD> md = mservice.MDList();
		ss.setAttribute("mlist", md);
		//output view 페이지로 이동
		resp.sendRedirect(req.getHeader("referer"));
		
	}

}
