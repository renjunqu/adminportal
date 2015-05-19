package cn.futuremove.adminportal.model.joyMove;

public class JoyCoupon{

	private Long couponid;
	private String mobileno;
	private java.math.BigDecimal couponnum;
	private java.sql.Timestamp couponexpdate;
	private java.sql.Timestamp overduetime;
	private Long delmark;

	public Long getCouponid(){
		 return this.couponid;
	}

	public void setCouponid(Long couponid){
		this.couponid = couponid;
	}

	public String getMobileno(){
		 return this.mobileno;
	}

	public void setMobileno(String mobileno){
		this.mobileno = mobileno;
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

	public Long getDelmark(){
		 return this.delmark;
	}

	public void setDelmark(Long delmark){
		this.delmark = delmark;
	}

}