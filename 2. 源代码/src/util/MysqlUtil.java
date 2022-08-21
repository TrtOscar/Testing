package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlUtil {
	
	public static Connection conn;
	// 数据库链接地址
	private static String dbUrl = "jdbc:mysql://localhost:3306/drug_inventory_manage?characterEncoding=utf-8";

	// 用户名
	private static String dbUserName = "root";

	// 密码
	private static String dbPassword = "1234";

	// 数据库驱动类
	private static String jdbcName = "com.mysql.jdbc.Driver";

	static {
		try {
			// 加载数据库驱动类
			Class.forName(jdbcName);
			conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
