package model;

/**
 * 药品销售信息
 * @author Administrator
 *
 */
public class DrugSale {
	
	// 编号
	private String id;
	
	// 药品编号
	private String drugid;
	
	// 药品名称
	private String drugname;
	
	// 价格
	private String price;
	
	// 数量
	private int num;
	
	// 支付金额
	private String paymoney;
	
	// 客户名
	private String customer;
	
	// 购买时间
	private String buytime;
	
	public DrugSale() {}

	public DrugSale(String id, String drugid, String drugname, String price, int num, String paymoney, String customer,
			String buytime) {
		super();
		this.id = id;
		this.drugid = drugid;
		this.drugname = drugname;
		this.price = price;
		this.num = num;
		this.paymoney = paymoney;
		this.customer = customer;
		this.buytime = buytime;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPaymoney() {
		return paymoney;
	}

	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getBuytime() {
		return buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}
	
	

}
