package model;

/**
 * 药品信息表
 * @author Administrator
 *
 */
public class DrugInfo {
	
	// 药品编号
	private String id;
	
	// 药品名称
	private String drugname;
	
	// 生产日期
	private String productdate;
	
	// 价格
	private String drugprice;
	
	// 使用期限
	private String uselimit;
	
	public DrugInfo() {}

	public DrugInfo(String id, String drugname, String productdate, String drugprice, String uselimit) {
		super();
		this.id = id;
		this.drugname = drugname;
		this.productdate = productdate;
		this.drugprice = drugprice;
		this.uselimit = uselimit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDrugname() {
		return drugname;
	}

	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}

	public String getProductdate() {
		return productdate;
	}

	public void setProductdate(String productdate) {
		this.productdate = productdate;
	}

	public String getDrugprice() {
		return drugprice;
	}

	public void setDrugprice(String drugprice) {
		this.drugprice = drugprice;
	}

	public String getUselimit() {
		return uselimit;
	}

	public void setUselimit(String uselimit) {
		this.uselimit = uselimit;
	}

	
}
