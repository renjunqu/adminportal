package cn.futuremove.adminportal.model.joyMove;

public class MbkCoupon{

	private Long couponid;
	private String userid;
	private java.math.BigDecimal couponnum;
	private java.sql.Timestamp couponexpdate;
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

	public java.math.BigDecimal getCouponnum(){
		 return this.couponnum;
	}

	public void setCouponnum(java.math.BigDecimal couponnum){
		this.couponnum = couponnum;
	}

	public java.sql.Timestamp getCouponexpdate(){
		 return this.couponexpdate;
	}

	public void setCouponexpdate(java.sql.Timestamp couponexpdate){
		this.couponexpdate = couponexpdate;
	}

	public java.sql.Timestamp getOverduetime(){
		 return this.overduetime;
	}

	public void setOverduetime(java.sql.Timestamp overduetime){
		this.overduetime = overduetime;
	}

}