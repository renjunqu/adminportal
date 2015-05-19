package cn.futuremove.adminportal.model.joyMove;

public class JoyDynamicpws{

	private Long codeid;
	private String mobileno;
	private String code;
	private java.sql.Timestamp createtime;
	private java.sql.Timestamp expirationtime;

	public Long getCodeid(){
		 return this.codeid;
	}

	public void setCodeid(Long codeid){
		this.codeid = codeid;
	}

	public String getMobileno(){
		 return this.mobileno;
	}

	public void setMobileno(String mobileno){
		this.mobileno = mobileno;
	}

	public String getCode(){
		 return this.code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public java.sql.Timestamp getCreatetime(){
		 return this.createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime){
		this.createtime = createtime;
	}

	public java.sql.Timestamp getExpirationtime(){
		 return this.expirationtime;
	}

	public void setExpirationtime(java.sql.Timestamp expirationtime){
		this.expirationtime = expirationtime;
	}

}