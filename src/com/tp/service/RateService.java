package com.tp.service;

import java.util.ArrayList;
import com.tp.dao.AccountDAO;
import com.tp.dao.RateDAO;
import com.tp.vo.Rate;
import com.tp.vo.Ratedirector;

public class RateService {
	private static RateService service = new RateService();
	public RateDAO dao = RateDAO.getInstance();
	
	private RateService() {}
	public static RateService getInstance() {
		return service;
	}
	 
	 public void RateInsert(String id, String Mtitle, String Rating, String Mcode) {
		 dao.RateInsert(id, Mtitle, Rating, Mcode);
	 }
	 
	 //
	 public Rate RateSearch(String id, String Mtitle) {
		 Rate rate = dao.RateSearch(id, Mtitle);
		 return rate;
	 }
	 
	 public void RateUpdate(String id, String Mtitle, String Rating) {
		 dao.RateUpdate(id, Mtitle, Rating);
	 }
	 public void RateDelete(String id, String Mtitle) {
		 dao.RateDelete(id, Mtitle);
	 }
	 public ArrayList<Rate> RateList(String id) {
		 ArrayList<Rate> list = dao.RateList(id);
		 return list;
	 }
	 public ArrayList<Ratedirector> Ratedirector(String id, String director) {
		 ArrayList<Ratedirector> list = dao.RateDirector(id,director);
		 return list;
	 }
}
