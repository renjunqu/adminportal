package cn.futuremove.adminportal.model.joyMove;

public class Address{

	private Long aid;
	private String address;
	private String postcode;
	private Long ownerid;

	public Long getAid(){
		 return this.aid;
	}

	public void setAid(Long aid){
		this.aid = aid;
	}

	public String getAddress(){
		 return this.address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getPostcode(){
		 return this.postcode;
	}

	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public Long getOwnerid(){
		 return this.ownerid;
	}

	public void setOwnerid(Long ownerid){
		this.ownerid = ownerid;
	}

}