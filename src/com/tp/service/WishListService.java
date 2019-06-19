package com.tp.service;

import java.util.ArrayList;

import com.tp.dao.WishListDAO;
import com.tp.vo.Account;
import com.tp.vo.WishList;

public class WishListService {
	private static WishListService service = new WishListService();
	public WishListDAO dao = WishListDAO.getInstance();
	
	private WishListService() {}
	public static WishListService getInstance() {
		return service;
	}
	
	public WishList WishSearch(String id) {
		 WishList wishlist = dao.WishSearch(id);
		 return wishlist;
	 }
	public void WishInsert(String id, String Mtitle, String mcode) {
		dao.WishInsert(id, Mtitle, mcode);
	}
	public void WishDelete(String id, String Mtitle) {
		dao.WishDelete(id, Mtitle);
	}
	 
	public ArrayList<WishList> WishList(String id) {
		ArrayList<WishList> list = dao.WishList(id);
		return list;
	}
 
}
