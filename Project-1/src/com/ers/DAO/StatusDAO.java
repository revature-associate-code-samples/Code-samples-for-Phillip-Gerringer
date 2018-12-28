/**
 * 
 */
package com.ers.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ers.beans.StatusBean;
import com.ers.io.ConnectionFactory;

/**
 * The data access layer object responsible for processing (CRUD) the StatusBean object
 * @author Phil
 *
 */
public class StatusDAO implements DAO<StatusBean, Integer>{

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getAll()
	 */
	@Override
	public List<StatusBean> getAll() {
		List<StatusBean> list = new ArrayList<StatusBean>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select status_id, name from status ";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				StatusBean bean = new StatusBean();
				bean.setStatus_id(rs.getInt(1));
				bean.setName(rs.getString(2));
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ers.DAO.DAO#getByID(java.lang.Object)
	 */
	@Override
	public StatusBean getByID(StatusBean obj) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getByID(java.io.Serializable)
	 */
	@Override
	public StatusBean getByID(Integer obj) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ers.DAO.DAO#insert(java.lang.Object)
	 */
	@Override
	public StatusBean insert(StatusBean obj) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ers.DAO.DAO#update(java.lang.Object)
	 */
	@Override
	public StatusBean update(StatusBean obj) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ers.DAO.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(StatusBean obj) {
		
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getCount(java.io.Serializable)
	 */
	@Override
	public int getCount(Integer obj) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getCount()
	 */
	@Override
	public int getCount() {
		return 0;
	}
}
