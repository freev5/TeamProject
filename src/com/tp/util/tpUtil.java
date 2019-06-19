package com.tp.util;

public class tpUtil {
	
	public String randomQuery() {
		String query = "";
		
		String[] randomQ = {
				"A","B","C","D","E"
		};
		//성인영화 노출 방지 벤
		int r = (int) (Math.random()*5);
		query = randomQ[r];
		return query;
	}
}
