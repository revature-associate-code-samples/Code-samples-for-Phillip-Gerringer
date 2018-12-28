/**
 * 
 */
package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.banking.beans.TypeBean;
import com.banking.io.ConnectionFactory;

/**
 * DAO access to the acme_type table
 * 
 * @author pgerringer
 *
 */
public class TypeDAO implements DAO<TypeBean, Integer> {

	/*
	 * (non-Javadoc)
	 * @see com.banking.dao.DAO#getAll()
	 */
	@Override
	public List<TypeBean> getAll() {
		List<TypeBean> list = new ArrayList<TypeBean>();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from acme_type";
			
			Statement stmt = conn.createStatement();
			
			
			//PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TypeBean bean = new TypeBean();
				bean.setId(rs.getInt(1));
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
	 * @see com.banking.dao.DAO#getByID(java.io.Serializable)
	 */
	@Override
	public TypeBean getByID(Integer id) {
		TypeBean bean=null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {		
			String sql = "select * from acme_type where type_id = ?";
			
			// prepared statement to avoid mal sql injection
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new TypeBean();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * @see com.banking.dao.DAO#insert(java.lang.Object)
	 */
	@Override
	public TypeBean insert(TypeBean obj) {
		// static table, no insert
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.banking.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public TypeBean update(TypeBean obj) {
		// static table, no update
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.banking.dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(TypeBean obj) {
		// static table, no delete
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.banking.dao.DAO#getAll(java.io.Serializable)
	 */
	@Override
	public List<TypeBean> getAll(Integer id) {
		// no need to get a type by id
		return null;
	}
}
