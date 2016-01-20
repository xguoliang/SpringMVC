package com.dao;

public class MySQLPage extends BasePage{

	public String getPage(String baseSQL, int from, int to) {

		return SqlUtils.getSql(baseSQL + " limit {0} ,{1}", new String[] {
				String.valueOf(from), String.valueOf(to) });
	}

}
