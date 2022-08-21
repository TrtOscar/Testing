package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.DrugInfo;
import util.MysqlUtil;

public class DrugInfoDao {
	
	/**
	 * 新增药品信息
	 * @param drugInfo
	 */
	public static int insertDrugInfo(DrugInfo drugInfo) {
		String sql = "insert into drugInfo VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);
			pst.setString(1, drugInfo.getId());
			pst.setString(2, drugInfo.getDrugname());
			pst.setString(3, drugInfo.getProductdate());
			pst.setString(4, drugInfo.getDrugprice());
			pst.setString(5, drugInfo.getUselimit());
			
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}
	
	/**
	 * 新增药品信息
	 * @param drugInfo
	 * @return
	 */
	public static int updateDrugInfo(DrugInfo drugInfo) {
		String sql = "update drugInfo set drugname=?,productdate=?,drugprice=?,uselimit=? where id=?";
		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);
			
			pst.setString(1, drugInfo.getDrugname());
			pst.setString(2, drugInfo.getProductdate());
			pst.setString(3, drugInfo.getDrugprice());
			pst.setString(4, drugInfo.getUselimit());
			pst.setString(5, drugInfo.getId());
			
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}
	
	/**
	 * 删除指定的药品信息
	 * @param studentid
	 * @return
	 */
	public static int deleteDrugInfo(String studentid) {
		String sql = "delete from drugInfo where id='"+studentid+"'";
		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);
			
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}
	
	/**
	 * 根据范围查询药品信息
	 * @param min
	 * @param max
	 * @return
	 */
	public static List<DrugInfo> getDrugInfos(String condition){
		String sql = "select * FROM drugInfo where 1=1 " + condition;
		
		List<DrugInfo> drugInfos = new ArrayList<DrugInfo>();
		
		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				DrugInfo drugInfo = null;
				String id = rs.getString("id");
				String drugname = rs.getString("drugname");
				String productdate = rs.getString("productdate");
				String drugprice = rs.getString("drugprice");
				String uselimit = rs.getString("uselimit");

				drugInfo = new DrugInfo(id, drugname, productdate, drugprice,uselimit);
				
				drugInfos.add(drugInfo);
			}
		} catch (SQLException e) {
			return null;
		}
		
		return drugInfos;
	}

}
