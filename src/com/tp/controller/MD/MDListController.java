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

public class MDListController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		MDService service = MDService.getInstance();
		ArrayList<MD> list = service.MDList();
		
		req.setAttribute("list", list);
		MDService mservice = MDService.getInstance();
		ArrayList<MD> md = mservice.MDList();
		ss.setAttribute("mlist", md);
		HttpUtil.forward(req, resp, "/AdminMovieList.jsp");
	}
}
