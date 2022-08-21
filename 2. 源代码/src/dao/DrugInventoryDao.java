package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.DrugInventory;
import util.MysqlUtil;

public class DrugInventoryDao {
	
	/**
	 * 新增药品信息
	 * @param drugInventory
	 */
	public static int insertDrugInventory(DrugInventory drugInventory) {
		String sql = "insert into drugInventory VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);
			pst.setString(1, drugInventory.getId());
			pst.setString(2, drugInventory.getDrugid());
			pst.setString(3, drugInventory.getDrugname());
			pst.setInt(4, drugInventory.getInventory());
			pst.setString(5, drugInventory.getDrugprice());
			
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}
	
	/**
	 * 新增药品信息
	 * @param drugInventory
	 * @return
	 */
	public static int updateDrugInventory(DrugInventory drugInventory) {
		String sql = "update drugInventory set drugid=?,drugname=?,inventory=?,drugprice=? where id=?";
		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);
			
			
			pst.setString(1, drugInventory.getDrugid());
			pst.setString(2, drugInventory.getDrugname());
			pst.setInt(3, drugInventory.getInventory());
			pst.setString(4, drugInventory.getDrugprice());
			pst.setString(5, drugInventory.getId());
			
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
	public static int deleteDrugInventory(String studentid) {
		String sql = "delete from drugInventory where id="+studentid;
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
	public static List<DrugInventory> getDrugInventorys(String condition){
		String sql = "select * FROM drugInventory where 1=1 " + condition;
		
		List<DrugInventory> drugInventorys = new ArrayList<DrugInventory>();
		
		try {
			PreparedStatement pst = (PreparedStatement) MysqlUtil.conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				DrugInventory drugInventory = null;
				String id = rs.getString("id");
				String drugid = rs.getString("drugid");
				String drugname = rs.getString("drugname");
				int inventory = rs.getInt("inventory");
				String drugprice = rs.getString("drugprice");

				drugInventory = new DrugInventory(id, drugid, drugname, inventory, drugprice);
				
				drugInventorys.add(drugInventory);
			}
		} catch (SQLException e) {
			return null;
		}
		
		return drugInventorys;
	}

}
