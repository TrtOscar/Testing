package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import model.User;
import util.MysqlUtil;

public class UserDao {
	
	/**
	 * 学生用户登录
	 * @param studentid
	 * @param password
	 * @return
	 */
	public static User userLogin(String name, String password, String role) {
		User student = null;
		String sql = "select * FROM user where name='" + name + "' and password='"+password+"' and role='" + role + "'";
		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				String id = rs.getString("id");
				String profession = rs.getString("profession");
				String sex = rs.getString("sex");
				
				student = new User(Integer.valueOf(id), name, profession, sex, password, role);
				
				return student;
			}
		} catch (SQLException e) {
			return null;
		}
		
		return student;
	}
	
}
