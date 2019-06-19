package com.tp.service;

import java.util.ArrayList;
import com.tp.dao.MDDAO;
import com.tp.vo.MD;

public class MDService {
	private static MDService service = new MDService();
	public MDDAO dao = MDDAO.getInstance();

	private MDService() {
	}

	public static MDService getInstance() {
		return service;
	}

	public void MDInsert(MD md) {
		dao.MDInsert(md);
	}

	//
	public MD MDSearch(String id) {
		MD md = dao.MDSearch(id);
		return md;
	}

	public void MDUpdate(MD md) {
		dao.MDUpdate(md);
	}

	public void MDDelete(String id) {
		dao.MDDelete(id);
	}

	public ArrayList<MD> MDList() {
		ArrayList<MD> list = dao.MDList();
		return list;
	}
}
