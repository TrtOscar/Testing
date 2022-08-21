package model;

/**
 * 药品库存信息
 * @author Administrator
 *
 */
public class DrugInventory {
	
	// 编号
	private String id;
	
	// 药品编号
	private String drugid;
	
	// 药品名称
	private String drugname;
	
	// 药品价格
	private String drugprice;
	
	// 库存量
	private int inventory;
	
	public DrugInventory() {}

	public DrugInventory(String id, String drugid, String drugname, int inventory,String drugprice) {
		super();
		this.id = id;
		this.drugid = drugid;
		this.drugname = drugname;
		this.inventory = inventory;
		this.drugprice = drugprice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDrugid() {
		return drugid;
	}

	public void setDrugid(String drugid) {
		this.drugid = drugid;
	}

	public String getDrugname() {
		return drugname;
	}

	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public String getDrugprice() {
		return drugprice;
	}

	public void setDrugprice(String drugprice) {
		this.drugprice = drugprice;
	}
}
