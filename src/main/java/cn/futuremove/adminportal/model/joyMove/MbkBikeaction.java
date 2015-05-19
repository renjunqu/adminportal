package cn.futuremove.adminportal.model.joyMove;

public class MbkBikeaction{

	private Long id;
	private String userid;
	private String bikecode;
	private String type;
	private java.sql.Timestamp requesttime;
	private Long delflag;

	public Long getId(){
		 return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getUserid(){
		 return this.userid;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getBikecode(){
		 return this.bikecode;
	}

	public void setBikecode(String bikecode){
		this.bikecode = bikecode;
	}

	public String getType(){
		 return this.type;
	}

	public void setType(String type){
		this.type = type;
	}

	public java.sql.Timestamp getRequesttime(){
		 return this.requesttime;
	}

	public void setRequesttime(java.sql.Timestamp requesttime){
		this.requesttime = requesttime;
	}

	public Long getDelflag(){
		 return this.delflag;
	}

	public void setDelflag(Long delflag){
		this.delflag = delflag;
	}

}