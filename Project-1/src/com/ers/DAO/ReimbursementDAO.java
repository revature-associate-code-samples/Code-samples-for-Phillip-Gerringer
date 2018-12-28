/**
 * 
 */
package com.ers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ers.beans.ReimbursementBean;
import com.ers.io.ConnectionFactory;
import com.ers.io.Log;
import com.ers.utils.ToolBelt;

/**
 * The data access layer object responsible for processing (CRUD) the ReimbursementBean object
 * @author Phil
 *
 */
public class ReimbursementDAO implements DAO<ReimbursementBean, Integer> {
	Logger log = Log.getInstance(this);
	
	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getAll()
	 */
	@Override
	public List<ReimbursementBean> getAll() {
		List<ReimbursementBean> list = new ArrayList<ReimbursementBean>();
		EmployeeDAO eDao = new EmployeeDAO();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select reimbursement_id, amount, submit_date, resolve_date, detail, " + 
					"submitter_id, resolver_id, r.status_id, s.name, r.type_id, t.name " + 
					"from reimbursement r " + 
					"join status s on s.status_id = r.status_id " + 
					"join type t on t.type_id = r.type_id ";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ReimbursementBean bean = new ReimbursementBean();
				bean.setReimbursement_id(rs.getInt(1));
				bean.setAmount(rs.getDouble(2));
				bean.setSubmit_date(ToolBelt.convertDate(rs.getDate(3)));  // to convert sql.Date to util.Date
				bean.setResolve_date(ToolBelt.convertDate(rs.getDate(4)));
				bean.setDetail(rs.getString(5));
				bean.getSubmitter().setEmployee_id(rs.getInt(6));
				bean.getResolver().setEmployee_id(rs.getInt(7));
				bean.getStatus().setStatus_id(rs.getInt(8));
				bean.getStatus().setName(rs.getString(9));
				bean.getType().setType_id(rs.getInt(10));
				bean.getType().setName(rs.getString(11));
				
				bean.setSubmitter(eDao.getByID(bean.getSubmitter().getEmployee_id()));
				bean.setResolver(eDao.getByID(bean.getResolver().getEmployee_id()));
				
				list.add(bean);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	/**
	 * returns all of the reimbursements for the employee_id
	 * @param employee_id
	 * @return
	 */
	public List<ReimbursementBean> getAll(int employee_id) {
		List<ReimbursementBean> list = new ArrayList<ReimbursementBean>();
		EmployeeDAO eDao = new EmployeeDAO();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select reimbursement_id, amount, submit_date, resolve_date, detail, " + 
					"submitter_id, resolver_id, r.status_id, s.name, r.type_id, t.name " + 
					"from reimbursement r " + 
					"join status s on s.status_id = r.status_id " + 
					"join type t on t.type_id = r.type_id " +
					"where submitter_id = ? ";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ReimbursementBean bean = new ReimbursementBean();
				bean.setReimbursement_id(rs.getInt(1));
				bean.setAmount(rs.getDouble(2));
				bean.setSubmit_date(ToolBelt.convertDate(rs.getDate(3)));  // to convert sql.Date to util.Date
				bean.setResolve_date(ToolBelt.convertDate(rs.getDate(4)));
				bean.setDetail(rs.getString(5));
				bean.getSubmitter().setEmployee_id(rs.getInt(6));
				bean.getResolver().setEmployee_id(rs.getInt(7));
				bean.getStatus().setStatus_id(rs.getInt(8));
				bean.getStatus().setName(rs.getString(9));
				bean.getType().setType_id(rs.getInt(10));
				bean.getType().setName(rs.getString(11));
				
				bean.setSubmitter(eDao.getByID(bean.getSubmitter().getEmployee_id()));
				bean.setResolver(eDao.getByID(bean.getResolver().getEmployee_id()));
				
				list.add(bean);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * returns all reimbursements that are in the status id of Submitted
	 * @return
	 */
	public List<ReimbursementBean> getAllSubmitted() {
		List<ReimbursementBean> list = new ArrayList<ReimbursementBean>();
		EmployeeDAO eDao = new EmployeeDAO();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select reimbursement_id, amount, submit_date, resolve_date, detail, " + 
					"submitter_id, resolver_id, r.status_id, s.name, r.type_id, t.name " + 
					"from reimbursement r " + 
					"join status s on s.status_id = r.status_id " + 
					"join type t on t.type_id = r.type_id "
					+ "where r.status_id = 1";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ReimbursementBean bean = new ReimbursementBean();
				bean.setReimbursement_id(rs.getInt(1));
				bean.setAmount(rs.getDouble(2));
				bean.setSubmit_date(ToolBelt.convertDate(rs.getDate(3)));  // to convert sql.Date to util.Date
				bean.setResolve_date(ToolBelt.convertDate(rs.getDate(4)));
				bean.setDetail(rs.getString(5));
				bean.getSubmitter().setEmployee_id(rs.getInt(6));
				bean.getResolver().setEmployee_id(rs.getInt(7));
				bean.getStatus().setStatus_id(rs.getInt(8));
				bean.getStatus().setName(rs.getString(9));
				bean.getType().setType_id(rs.getInt(10));
				bean.getType().setName(rs.getString(11));
				
				bean.setSubmitter(eDao.getByID(bean.getSubmitter().getEmployee_id()));
				bean.setResolver(eDao.getByID(bean.getResolver().getEmployee_id()));
				
				list.add(bean);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ers.DAO.DAO#getByID(java.lang.Object)
	 */
	@Override
	public ReimbursementBean getByID(ReimbursementBean obj) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getByID(java.io.Serializable)
	 */
	@Override
	public ReimbursementBean getByID(Integer obj) {
		EmployeeDAO eDao = new EmployeeDAO();
		ReimbursementBean bean = new ReimbursementBean();
		log.debug("reimbursement_id = " + obj);

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select reimbursement_id, amount, submit_date, resolve_date, detail, " + 
					"submitter_id, resolver_id, r.status_id, s.name, r.type_id, t.name " + 
					"from reimbursement r " + 
					"join status s on s.status_id = r.status_id " + 
					"join type t on t.type_id = r.type_id " +
					"where r.reimbursement_id = ? ";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, obj);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setReimbursement_id(rs.getInt(1));
				bean.setAmount(rs.getDouble(2));
				bean.setSubmit_date(ToolBelt.convertDate(rs.getDate(3)));  // to convert sql.Date to util.Date
				bean.setResolve_date(ToolBelt.convertDate(rs.getDate(4)));
				bean.setDetail(rs.getString(5));
				bean.getSubmitter().setEmployee_id(rs.getInt(6));
				bean.getResolver().setEmployee_id(rs.getInt(7));
				bean.getStatus().setStatus_id(rs.getInt(8));
				bean.getStatus().setName(rs.getString(9));
				bean.getType().setType_id(rs.getInt(10));
				bean.getType().setName(rs.getString(11));
				
				bean.setSubmitter(eDao.getByID(bean.getSubmitter().getEmployee_id()));
				bean.setResolver(eDao.getByID(bean.getResolver().getEmployee_id()));
				log.debug(bean);
			}

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}
	
	/**
	 * pulls a record that matches columns that could be considered unique
	 * !!!mostly used to identify the row that was inserted when we insert
	 * @param obj
	 * @return
	 */
	public ReimbursementBean getByUniques(ReimbursementBean obj) {
		EmployeeDAO eDao = new EmployeeDAO();
		ReimbursementBean bean = new ReimbursementBean();
		log.debug("reimbursement_id = " + obj);

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select reimbursement_id, amount, submit_date, resolve_date, detail, " + 
					"submitter_id, resolver_id, r.status_id, s.name, r.type_id, t.name " + 
					"from reimbursement r " + 
					"join status s on s.status_id = r.status_id " + 
					"join type t on t.type_id = r.type_id " +
					"where r.submitter_id = ? "
					+ "and r.amount = ? "
					+ "and r.detail = ? "
					+ "and r.status_id = ? "
					+ "and r.type_id = ? ";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, obj.getSubmitter().getEmployee_id());
			ps.setDouble(2, obj.getAmount());
			ps.setString(3, obj.getDetail());
			ps.setInt(4, obj.getStatus().getStatus_id());
			ps.setInt(5, obj.getType().getType_id());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setReimbursement_id(rs.getInt(1));
				bean.setAmount(rs.getDouble(2));
				bean.setSubmit_date(ToolBelt.convertDate(rs.getDate(3)));  // to convert sql.Date to util.Date
				bean.setResolve_date(ToolBelt.convertDate(rs.getDate(4)));
				bean.setDetail(rs.getString(5));
				bean.getSubmitter().setEmployee_id(rs.getInt(6));
				bean.getResolver().setEmployee_id(rs.getInt(7));
				bean.getStatus().setStatus_id(rs.getInt(8));
				bean.getStatus().setName(rs.getString(9));
				bean.getType().setType_id(rs.getInt(10));
				bean.getType().setName(rs.getString(11));
				
				bean.setSubmitter(eDao.getByID(bean.getSubmitter().getEmployee_id()));
				bean.setResolver(eDao.getByID(bean.getResolver().getEmployee_id()));
				log.debug(bean);
			}

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ers.DAO.DAO#insert(java.lang.Object)
	 */
	@Override
	public ReimbursementBean insert(ReimbursementBean obj) {
		ReimbursementBean bean = new ReimbursementBean();
		log.debug("bean coming in " + obj);

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "insert into reimbursement (amount,detail,submitter_id,status_id,type_id) values (?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, obj.getAmount());
			ps.setString(2, obj.getDetail());
			ps.setInt(3, obj.getSubmitter().getEmployee_id());
			ps.setInt(4, obj.getStatus().getStatus_id());
			ps.setInt(5,  obj.getType().getType_id());

			int rs = ps.executeUpdate();
		
			if(rs > 0) {
				//bean = this.getByID(obj.getReimbursement_id());
				// no way to uniquely identify the record that was inserted
				// so do the best we can...cross your fingers
				bean = this.getByUniques(obj);
			}
			log.debug(bean);
		} 
		catch (SQLException e) {
			e.printStackTrace();;
		}

		log.debug("bean going out " + bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ers.DAO.DAO#update(java.lang.Object)
	 */
	@Override
	public ReimbursementBean update(ReimbursementBean obj) {
		// the user can only update specific entries
		
		ReimbursementBean bean = new ReimbursementBean();
		log.debug("bean coming in " + obj);

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "update reimbursement "
					+ "set amount = ?, "
					+ "resolve_date = ?, "
					+ "detail = ?, "
					+ "resolver_id = ?, "
					+ "status_id = ?, "
					+ "type_id = ? "
					+ "where reimbursement_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, obj.getAmount());
			if (obj.getResolve_date() == null) {
				ps.setDate(2, null);
			}
			else {
				ps.setDate(2, ToolBelt.convertDate(obj.getNatural_Resolve_date()));
			}
			ps.setString(3, obj.getDetail());
			if(obj.getResolver().getEmployee_id() > 0) {
				ps.setInt(4, obj.getResolver().getEmployee_id());
			}
			else {
				ps.setString(4, null);
			}
			ps.setInt(5, obj.getStatus().getStatus_id());
			ps.setInt(6,  obj.getType().getType_id());
			ps.setInt(7,  obj.getReimbursement_id());

			int rs = ps.executeUpdate();
		
			if(rs > 0) {
				bean = this.getByID(obj.getReimbursement_id());
			}
			log.debug(bean);
		} 
		catch (SQLException e) {
			e.printStackTrace();;
		}

		log.debug("bean going out " + bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ers.DAO.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(ReimbursementBean obj) {
		
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getCount(java.io.Serializable)
	 */
	@Override
	public int getCount(Integer obj) {
		log.debug("in getCount(" + obj + ")");
		int count = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select count(*) from reimbursement "
					+ "where submitter_id = ? and status_id = 1 "
					+ "group by submitter_id ";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, obj);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
				log.debug("in getCount(" + obj + ") = " + count);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getCount()
	 */
	@Override
	public int getCount() {
		log.debug("in getCount()");
		int count = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select count(*) from reimbursement "
					+ "where status_id = 1 "
					+ "group by status_id ";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				count = rs.getInt(1);
				log.debug("in getCount() = " + count);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}
}
