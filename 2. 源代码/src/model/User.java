package model;

/**
 * 用户信息表
 * @author Administrator
 *
 */
public class User {
	
	// 主键
	private int id;
	
	// 姓名
	private String name;
	
	// 专业
	private String profession;
	
	// 性别
	private String sex;
	
	// 密码
	private String password;
	
	// 角色信息
	private String role;
	
	public User() {}

	public User(int id, String name, String profession, String sex, String password, String role) {
		this.id = id;
		this.name = name;
		this.profession = profession;
		this.sex = sex;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
