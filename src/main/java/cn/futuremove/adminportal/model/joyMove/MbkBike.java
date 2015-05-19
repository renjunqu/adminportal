package cn.futuremove.adminportal.model.joyMove;

public class MbkBike{

	private Long bikeid;
	private Long clusterid;
	private java.math.BigDecimal positionx;
	private java.math.BigDecimal positiony;
	private String vendor;
	private java.sql.Date madedate;
	private String color;
	private String type;
	private Long distance;
	private java.math.BigDecimal price;
	private java.math.BigDecimal weight;
	private String twodcode;
	private Long power;
	private Long lockstatus;
	private Long registrystatus;

	public Long getBikeid(){
		 return this.bikeid;
	}

	public void setBikeid(Long bikeid){
		this.bikeid = bikeid;
	}

	public Long getClusterid(){
		 return this.clusterid;
	}

	public void setClusterid(Long clusterid){
		this.clusterid = clusterid;
	}

	public java.math.BigDecimal getPositionx(){
		 return this.positionx;
	}

	public void setPositionx(java.math.BigDecimal positionx){
		this.positionx = positionx;
	}

	public java.math.BigDecimal getPositiony(){
		 return this.positiony;
	}

	public void setPositiony(java.math.BigDecimal positiony){
		this.positiony = positiony;
	}

	public String getVendor(){
		 return this.vendor;
	}

	public void setVendor(String vendor){
		this.vendor = vendor;
	}

	public java.sql.Date getMadedate(){
		 return this.madedate;
	}

	public void setMadedate(java.sql.Date madedate){
		this.madedate = madedate;
	}

	public String getColor(){
		 return this.color;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getType(){
		 return this.type;
	}

	public void setType(String type){
		this.type = type;
	}

	public Long getDistance(){
		 return this.distance;
	}

	public void setDistance(Long distance){
		this.distance = distance;
	}

	public java.math.BigDecimal getPrice(){
		 return this.price;
	}

	public void setPrice(java.math.BigDecimal price){
		this.price = price;
	}

	public java.math.BigDecimal getWeight(){
		 return this.weight;
	}

	public void setWeight(java.math.BigDecimal weight){
		this.weight = weight;
	}

	public String getTwodcode(){
		 return this.twodcode;
	}

	public void setTwodcode(String twodcode){
		this.twodcode = twodcode;
	}

	public Long getPower(){
		 return this.power;
	}

	public void setPower(Long power){
		this.power = power;
	}

	public Long getLockstatus(){
		 return this.lockstatus;
	}

	public void setLockstatus(Long lockstatus){
		this.lockstatus = lockstatus;
	}

	public Long getRegistrystatus(){
		 return this.registrystatus;
	}

	public void setRegistrystatus(Long registrystatus){
		this.registrystatus = registrystatus;
	}

}