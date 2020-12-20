package model;

public class Customer {
	private String id;
	private String name;
	private String password;
	private String tel;

	public Customer(String id, String name, String password, String tel){
		this.id = id;
		this.name = name;
		this.password = password;
		this.tel = tel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
