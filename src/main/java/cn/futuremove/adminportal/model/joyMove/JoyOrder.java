package cn.futuremove.adminportal.model.joyMove;

public class JoyOrder{

	private Long id;
	private String mobileno;
	private Long carid;
	private java.sql.Timestamp starttime;
	private java.sql.Timestamp stoptime;
	private Long delmark;
	private Long rentstatus;
	private Long type;
	private Long batonmode;
	private Long state;
	private String carvinnum;
	private String destination;

	public Long getId(){
		 return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getMobileno(){
		 return this.mobileno;
	}

	public void setMobileno(String mobileno){
		this.mobileno = mobileno;
	}

	public Long getCarid(){
		 return this.carid;
	}

	public void setCarid(Long carid){
		this.carid = carid;
	}

	public java.sql.Timestamp getStarttime(){
		 return this.starttime;
	}

	public void setStarttime(java.sql.Timestamp starttime){
		this.starttime = starttime;
	}

	public java.sql.Timestamp getStoptime(){
		 return this.stoptime;
	}

	public void setStoptime(java.sql.Timestamp stoptime){
		this.stoptime = stoptime;
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

	public Long getType(){
		 return this.type;
	}

	public void setType(Long type){
		this.type = type;
	}

	public Long getBatonmode(){
		 return this.batonmode;
	}

	public void setBatonmode(Long batonmode){
		this.batonmode = batonmode;
	}

	public Long getState(){
		 return this.state;
	}

	public void setState(Long state){
		this.state = state;
	}

	public String getCarvinnum(){
		 return this.carvinnum;
	}

	public void setCarvinnum(String carvinnum){
		this.carvinnum = carvinnum;
	}

	public String getDestination(){
		 return this.destination;
	}

	public void setDestination(String destination){
		this.destination = destination;
	}

}