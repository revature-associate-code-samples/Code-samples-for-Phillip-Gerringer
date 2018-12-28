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

import com.ers.beans.TypeBean;
import com.ers.io.ConnectionFactory;

/**
 * The data access layer object responsible for processing (CRUD) the TypeBean object
 * @author Phil
 *
 */
public class TypeDAO implements DAO<TypeBean, Integer>{

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getAll()
	 */
	@Override
	public List<TypeBean> getAll() {
		List<TypeBean> list = new ArrayList<TypeBean>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select type_id, name from type ";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				TypeBean bean = new TypeBean();
				bean.setType_id(rs.getInt(1));
				bean.setName(rs.getString(2));
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
	public TypeBean getByID(TypeBean obj) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#getByID(java.io.Serializable)
	 */
	@Override
	public TypeBean getByID(Integer obj) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#insert(java.lang.Object)
	 */
	@Override
	public TypeBean insert(TypeBean obj) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#update(java.lang.Object)
	 */
	@Override
	public TypeBean update(TypeBean obj) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ers.DAO.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(TypeBean obj) {
		
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
