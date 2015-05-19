package cn.futuremove.adminportal.model.joyMove;

public class JoyReserveorder{

	private Long id;
	private String mobileno;
	private java.sql.Timestamp starttime;
	private Long carid;
	private Long delflag;
	private String carvinnum;

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

	public java.sql.Timestamp getStarttime(){
		 return this.starttime;
	}

	public void setStarttime(java.sql.Timestamp starttime){
		this.starttime = starttime;
	}

	public Long getCarid(){
		 return this.carid;
	}

	public void setCarid(Long carid){
		this.carid = carid;
	}

	public Long getDelflag(){
		 return this.delflag;
	}

	public void setDelflag(Long delflag){
		this.delflag = delflag;
	}

	public String getCarvinnum(){
		 return this.carvinnum;
	}

	public void setCarvinnum(String carvinnum){
		this.carvinnum = carvinnum;
	}

}