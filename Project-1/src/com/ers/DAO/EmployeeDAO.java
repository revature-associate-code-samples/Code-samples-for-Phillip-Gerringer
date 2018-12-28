/**
 * 
 */
package com.ers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ers.beans.EmployeeBean;

import com.ers.io.ConnectionFactory;
import com.ers.io.Log;

/**
 * The data access layer object responsible for processing (CRUD) the EmployeeBean object
 * @author Phil
 *
 */
public class EmployeeDAO implements DAO<EmployeeBean, Integer> {
	Logger log = Log.getInstance(this);
	
	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getAll()
	 */
	@Override
	public List<EmployeeBean> getAll() {
		List<EmployeeBean> list = new ArrayList<EmployeeBean>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select employee_id, first_name, last_name, user_name, password, email, role_id, name " + 
					"from employee " + 
					"natural join role ";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				EmployeeBean bean = new EmployeeBean();
				bean.setEmployee_id(rs.getInt(1));
				bean.setFirst_name(rs.getString(2));
				bean.setLast_name(rs.getString(3));
				bean.setUser_name(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setEmail(rs.getString(6));
				bean.getRole().setRole_id(rs.getInt(7));
				bean.getRole().setName(rs.getString(8));
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getByID(java.lang.Object)
	 */
	@Override
	public EmployeeBean getByID(EmployeeBean obj) {
		EmployeeBean bean = new EmployeeBean();

		if (obj.getEmployee_id() == 0) {
			// then we are trying to login (only user_name and password present)
			try (Connection conn = ConnectionFactory.getInstance().getConnection()){
				String userID = obj.getUser_name();
				String userPassword = obj.getPassword();

				String sql = "select employee_id, first_name, last_name, user_name, password, email, role_id, name " + 
						"from employee " + 
						"natural join role " + 
						"where user_name = ? and password = ?";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, userID);
				ps.setString(2, userPassword);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					bean.setEmployee_id(rs.getInt(1));
					bean.setFirst_name(rs.getString(2));
					bean.setLast_name(rs.getString(3));
					bean.setUser_name(rs.getString(4));
					bean.setPassword(rs.getString(5));
					bean.setEmail(rs.getString(6));
					bean.getRole().setRole_id(rs.getInt(7));
					bean.getRole().setName(rs.getString(8));
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			try (Connection conn = ConnectionFactory.getInstance().getConnection()){
				int employee_id = obj.getEmployee_id();

				String sql = "select employee_id, first_name, last_name, user_name, password, email, role_id, name " + 
						"from employee " + 
						"natural join role " + 
						"where employee_id = ?";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, employee_id);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					bean.setEmployee_id(rs.getInt(1));
					bean.setFirst_name(rs.getString(2));
					bean.setLast_name(rs.getString(3));
					bean.setUser_name(rs.getString(4));
					bean.setPassword(rs.getString(5));
					bean.setEmail(rs.getString(6));
					bean.getRole().setRole_id(rs.getInt(7));
					bean.getRole().setName(rs.getString(8));
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return bean;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getByID(java.io.Serializable)
	 */
	@Override
	public EmployeeBean getByID(Integer obj) {
		EmployeeBean bean = new EmployeeBean();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "select employee_id, first_name, last_name, user_name, password, email, role_id, name " + 
					"from employee " + 
					"natural join role " + 
					"where employee_id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, obj);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setEmployee_id(rs.getInt(1));
				bean.setFirst_name(rs.getString(2));
				bean.setLast_name(rs.getString(3));
				bean.setUser_name(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setEmail(rs.getString(6));
				bean.getRole().setRole_id(rs.getInt(7));
				bean.getRole().setName(rs.getString(8));
			}

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	/**
	 * returns the employee found by just passing in the user name
	 * @param obj
	 * @return
	 */
	public EmployeeBean getByID(String obj) {
		EmployeeBean bean = new EmployeeBean();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String userID = obj;

			String sql = "select employee_id, first_name, last_name, user_name, password, email, role_id, name " + 
					"from employee " + 
					"natural join role " + 
					"where user_name = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userID);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setEmployee_id(rs.getInt(1));
				bean.setFirst_name(rs.getString(2));
				bean.setLast_name(rs.getString(3));
				bean.setUser_name(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setEmail(rs.getString(6));
				bean.getRole().setRole_id(rs.getInt(7));
				bean.getRole().setName(rs.getString(8));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#insert(java.lang.Object)
	 */
	@Override
	public EmployeeBean insert(EmployeeBean obj) throws SQLIntegrityConstraintViolationException {
		EmployeeBean bean = new EmployeeBean();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "insert into employee (first_name, last_name, user_name, password, email, role_id) " + 
					"values (?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getFirst_name());
			ps.setString(2, obj.getLast_name());
			ps.setString(3, obj.getUser_name());
			ps.setString(4, obj.getPassword());
			ps.setString(5,  obj.getEmail());
			ps.setInt(6, obj.getRole().getRole_id());

			int rs = ps.executeUpdate();

			if(rs > 0) {
				bean = this.getByID(obj);
			}
		} 
		catch (SQLIntegrityConstraintViolationException se) {
			throw se;
		}
		catch (SQLException e) {
			e.printStackTrace();;
		}

		return bean;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#update(java.lang.Object)
	 */
	@Override
	public EmployeeBean update(EmployeeBean obj) throws SQLIntegrityConstraintViolationException {
		EmployeeBean bean = new EmployeeBean();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "update employee " + 
					"set first_name=?, last_name=?, user_name=?, " + 
					"password=?, email=?, role_id=? " + 
					"where employee_id = ? ";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getFirst_name());
			ps.setString(2, obj.getLast_name());
			ps.setString(3, obj.getUser_name());
			ps.setString(4, obj.getPassword());
			ps.setString(5,  obj.getEmail());
			ps.setInt(6, obj.getRole().getRole_id());
			ps.setInt(7, obj.getEmployee_id());

			int rs = ps.executeUpdate();

			if(rs > 0) {
				bean = this.getByID(obj);
			}
		} 
		catch (SQLIntegrityConstraintViolationException se) {
			throw se;
		}
		catch (SQLException e) {
			e.printStackTrace();;
		}

		// if bean is empty, means the save failed
		return bean;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(EmployeeBean obj) {
		
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
