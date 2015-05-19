package cn.futuremove.adminportal.model.joyMove;

public class MbkOrders{

	private Long orderid;
	private String userid;
	private Long bikeid;
	private java.math.BigDecimal startpositionx;
	private java.math.BigDecimal startpositiony;
	private java.sql.Timestamp starttime;
	private Long totaldistance;
	private java.math.BigDecimal calorie;
	private Long totalduration;
	private java.sql.Timestamp stoptime;
	private java.math.BigDecimal stoppositionx;
	private java.math.BigDecimal stoppositiony;
	private java.math.BigDecimal totalfee;
	private Long delmark;
	private Long rentstatus;
	private Long lockmiliseconds;

	public Long getOrderid(){
		 return this.orderid;
	}

	public void setOrderid(Long orderid){
		this.orderid = orderid;
	}

	public String getUserid(){
		 return this.userid;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public Long getBikeid(){
		 return this.bikeid;
	}

	public void setBikeid(Long bikeid){
		this.bikeid = bikeid;
	}

	public java.math.BigDecimal getStartpositionx(){
		 return this.startpositionx;
	}

	public void setStartpositionx(java.math.BigDecimal startpositionx){
		this.startpositionx = startpositionx;
	}

	public java.math.BigDecimal getStartpositiony(){
		 return this.startpositiony;
	}

	public void setStartpositiony(java.math.BigDecimal startpositiony){
		this.startpositiony = startpositiony;
	}

	public java.sql.Timestamp getStarttime(){
		 return this.starttime;
	}

	public void setStarttime(java.sql.Timestamp starttime){
		this.starttime = starttime;
	}

	public Long getTotaldistance(){
		 return this.totaldistance;
	}

	public void setTotaldistance(Long totaldistance){
		this.totaldistance = totaldistance;
	}

	public java.math.BigDecimal getCalorie(){
		 return this.calorie;
	}

	public void setCalorie(java.math.BigDecimal calorie){
		this.calorie = calorie;
	}

	public Long getTotalduration(){
		 return this.totalduration;
	}

	public void setTotalduration(Long totalduration){
		this.totalduration = totalduration;
	}

	public java.sql.Timestamp getStoptime(){
		 return this.stoptime;
	}

	public void setStoptime(java.sql.Timestamp stoptime){
		this.stoptime = stoptime;
	}

	public java.math.BigDecimal getStoppositionx(){
		 return this.stoppositionx;
	}

	public void setStoppositionx(java.math.BigDecimal stoppositionx){
		this.stoppositionx = stoppositionx;
	}

	public java.math.BigDecimal getStoppositiony(){
		 return this.stoppositiony;
	}

	public void setStoppositiony(java.math.BigDecimal stoppositiony){
		this.stoppositiony = stoppositiony;
	}

	public java.math.BigDecimal getTotalfee(){
		 return this.totalfee;
	}

	public void setTotalfee(java.math.BigDecimal totalfee){
		this.totalfee = totalfee;
	}

	public Long getDelmark(){
		 return this.delmark;
	}

	public void setDelmark(Long delmark){
		this.delmark = delmark;
	}

	public Long getRentstatus(){
		 return this.rentstatus;
	}

	public void setRentstatus(Long rentstatus){
		this.rentstatus = rentstatus;
	}

	public Long getLockmiliseconds(){
		 return this.lockmiliseconds;
	}

	public void setLockmiliseconds(Long lockmiliseconds){
		this.lockmiliseconds = lockmiliseconds;
	}

}