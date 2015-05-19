package cn.futuremove.adminportal.model.joyMove;

public class MbkPayhistory{

	private Long couponid;
	private String userid;
	private Long paymoney;
	private java.sql.Timestamp overduetime;

	public Long getCouponid(){
		 return this.couponid;
	}

	public void setCouponid(Long couponid){
		this.couponid = couponid;
	}

	public String getUserid(){
		 return this.userid;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public Long getPaymoney(){
		 return this.paymoney;
	}

	public void setPaymoney(Long paymoney){
		this.paymoney = paymoney;
	}

	public java.sql.Timestamp getOverduetime(){
		 return this.overduetime;
	}

	public void setOverduetime(java.sql.Timestamp overduetime){
		this.overduetime = overduetime;
	}

}