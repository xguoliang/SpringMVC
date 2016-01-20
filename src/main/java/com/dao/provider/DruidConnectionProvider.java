package com.dao.provider;

import java.sql.Connection;

import javax.sql.DataSource;

import com.dao.provider.ConnectionProvider;

public class DruidConnectionProvider implements ConnectionProvider {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}

}