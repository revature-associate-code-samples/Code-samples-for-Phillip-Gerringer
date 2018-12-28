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

import com.ers.beans.RoleBean;
import com.ers.io.ConnectionFactory;

/**
 * The data access layer object responsible for processing (CRUD) the RoleBean object
 * @author Phil
 *
 */
public class RoleDAO implements DAO<RoleBean, Integer>{

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getAll()
	 */
	@Override
	public List<RoleBean> getAll() {
		List<RoleBean> list = new ArrayList<RoleBean>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select role_id, name from role ";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				RoleBean bean = new RoleBean();
				bean.setRole_id(rs.getInt(1));
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
	public RoleBean getByID(RoleBean obj) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getByID(java.io.Serializable)
	 */
	@Override
	public RoleBean getByID(Integer obj) {
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ers.DAO.DAO#insert(java.lang.Object)
	 */
	@Override
	public RoleBean insert(RoleBean obj) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ers.DAO.DAO#update(java.lang.Object)
	 */
	@Override
	public RoleBean update(RoleBean obj) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ers.DAO.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(RoleBean obj) {
		
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
