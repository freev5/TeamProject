package com.tp.util;

public class tpUtil {
	
	public String randomQuery() {
		String query = "";
		
		String[] randomQ = {
				"A","B","C","D","E"
		};
		//���ο�ȭ ���� ���� ��
		int r = (int) (Math.random()*5);
		query = randomQ[r];
		return query;
	}
}
