package com.tp.controller.MD;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.controller.Controller;
import com.tp.controller.HttpUtil;
import com.tp.service.MDService;
import com.tp.vo.MD;

public class MDInsertController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int mid = Integer.parseInt(req.getParameter("mid"));
		String Mtitle = req.getParameter("Mtitle");
		int Mcode = Integer.parseInt(req.getParameter("Mcode"));
		String link = req.getParameter("link");
		String image = req.getParameter("image");
		String subtitle = req.getParameter("subtitle");
		String pubDate = req.getParameter("pubDate");
		String director = req.getParameter("director");
		String actor = req.getParameter("actor");
		String userRating = req.getParameter("userRating");
		String genre = req.getParameter("genre");
		String info = req.getParameter("info");

		
		MD md;

		MDService service = MDService.getInstance();
		md = service.MDSearch(Mtitle);
		if(md==null) {
		md = new MD();
		md.setMid(mid);
		md.setMtitle(Mtitle);
		md.setMcode(Mcode);
		md.setLink(link);
		md.setImage(image);
		md.setSubtitle(subtitle);
		md.setPubDate(pubDate);
		md.setDirector(director);
		md.setActor(actor);
		md.setUserRating(userRating);
		md.setGenre(genre);
		md.setInfo(info);
		service.MDInsert(md);
		}

		//output view 페이지로 이동
		req.setAttribute("mid", mid);
		HttpUtil.forward(req, resp, "/Login.jsp");

	}

	private Object setMid(int mid) {
		// TODO Auto-generated method stub
		return null;
	}

}
