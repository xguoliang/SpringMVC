package com.dao.page;
public interface IPage {

	String synthesisPage(String baseSQL, int from, int pageSize);

}