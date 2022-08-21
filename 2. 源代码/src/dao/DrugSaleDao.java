package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.DrugSale;
import util.MysqlUtil;

public class DrugSaleDao {

	/**
	 * 新增药品成绩
	 * 
	 * @param drugSale
	 */
	public static int insertDrugSale(DrugSale drugSale) {
		String sql = "insert into drugSale VALUES(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);
			pst.setString(1, drugSale.getId());
			pst.setString(2, drugSale.getDrugid());
			pst.setString(3, drugSale.getDrugname());
			pst.setString(4, drugSale.getPrice());
			pst.setInt(5, drugSale.getNum());
			pst.setString(6, drugSale.getPaymoney());
			pst.setString(7, drugSale.getCustomer());
			pst.setString(8, drugSale.getBuytime());
			

			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}

	/**
	 * 新增药品信息
	 * 
	 * @param drugSale
	 * @return
	 */
	public static int updateDrugSale(DrugSale drugSale) {
		String sql = "update drugSale set drugid=?,drugname=?,price=?,num=?,paymoney=?,customer=?,buytime=? where id=?";
		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);
			
			pst.setString(1, drugSale.getDrugid());
			pst.setString(2, drugSale.getDrugname());
			pst.setString(3, drugSale.getPrice());
			pst.setInt(4, drugSale.getNum());
			pst.setString(5, drugSale.getPaymoney());
			pst.setString(6, drugSale.getCustomer());
			pst.setString(7, drugSale.getBuytime());
			pst.setString(8, drugSale.getId());

			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}

	/**
	 * 删除指定的药品信息
	 * 
	 * @param studentid
	 * @return
	 */
	public static int deleteDrugSale(String id) {
		String sql = "delete from drugSale where id='" + id + "'";
		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);

			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}

	/**
	 * 根据范围查询药品销售信息
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static List<DrugSale> getDrugSales(String condition) {
		String sql = "select * FROM drugSale where 1=1 " + condition;

		List<DrugSale> drugSales = new ArrayList<DrugSale>();

		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				DrugSale drugSale = null;
				String id = rs.getString("id");
				String drugid = rs.getString("drugid");
				String drugname = rs.getString("drugname");
				String price = rs.getString("price");
				int num = rs.getInt("num");
				String paymoney = rs.getString("paymoney");
				String customer = rs.getString("customer");
				String buytime = rs.getString("buytime");

				drugSale = new DrugSale(id, drugid, drugname, price, num, paymoney, customer, buytime);

				drugSales.add(drugSale);
			}
		} catch (SQLException e) {
			return null;
		}

		return drugSales;
	}

}
