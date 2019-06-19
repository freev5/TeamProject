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

public class MDSearchController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		String id = req.getParameter("id");
		String job = req.getParameter("job");
		String path = null;
		
		if(job.equals("Search")) {
			path="/MDSearch.jsp";
		}
		else if (job.equals("Update")) {
			path="/MDUpdate.jsp";
		}
		else if (job.equals("Delete")) {
			path="/MDDelete.jsp";
		}
		
		//��ȿ�� üũ
		if(id.isEmpty()) {
			req.setAttribute("error", "ID�� �Է����ֽñ� �ٶ��ϴ�!");
			HttpUtil.forward(req, resp, path);
			return;
		}
		
		//service ��ü�� �޼ҵ� ȣ��
		MDService service = MDService.getInstance();
		MD md = service.MDSearch(id);
		
		MDService mservice = MDService.getInstance();
		ArrayList<MD> mdd = mservice.MDList();
		ss.setAttribute("mlist", mdd);
		
		//output view �������� �̵�
		if(md == null) {
			req.setAttribute("result", "�˻��� ������ �����ϴ�!");
		}
		req.setAttribute("MD", md);
		
		if(job.equals("search")) {
			path="/result/MDSearchOutput.jsp";
		}
		resp.sendRedirect(req.getHeader("referer"));
		
	}

}
