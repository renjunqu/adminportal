package cn.futuremove.adminportal.model.joyMove;

public class User{

	private Long uid;
	private String name;
	private Long age;
	private String phone;
	private String passwd;

	public Long getUid(){
		 return this.uid;
	}

	public void setUid(Long uid){
		this.uid = uid;
	}

	public String getName(){
		 return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Long getAge(){
		 return this.age;
	}

	public void setAge(Long age){
		this.age = age;
	}

	public String getPhone(){
		 return this.phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPasswd(){
		 return this.passwd;
	}

	public void setPasswd(String passwd){
		this.passwd = passwd;
	}

}