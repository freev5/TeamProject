package com.tp.service;

import java.util.ArrayList;
import com.tp.dao.AccountDAO;
import com.tp.vo.Account;

public class AccountService {
	private static AccountService service = new AccountService();
	public AccountDAO dao = AccountDAO.getInstance();
	
	private AccountService() {}
	public static AccountService getInstance() {
		return service;
	}
	 
	 public void AccountRegist(Account account) {
		 dao.AccountRegist(account);
	 }
	 
	 //
	 public Account AccountSearch(String id) {
		 Account account = dao.AccountSearch(id);
		 return account;
	 }
	 
	 public void AccountUpdate(Account account) {
		 dao.AccountUpdate(account);
	 }
	 public void AccountDelete(String id) {
		 dao.AccountDelete(id);
	 }
	 public ArrayList<Account> AccountList() {
		 ArrayList<Account> list = dao.AccountList();
		 return list;
	 }
}
