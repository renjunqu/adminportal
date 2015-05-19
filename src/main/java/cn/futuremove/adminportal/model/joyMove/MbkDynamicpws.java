package cn.futuremove.adminportal.model.joyMove;

public class MbkDynamicpws{

	private Long codeid;
	private String phoneno;
	private String code;
	private java.sql.Timestamp createtime;

	public Long getCodeid(){
		 return this.codeid;
	}

	public void setCodeid(Long codeid){
		this.codeid = codeid;
	}

	public String getPhoneno(){
		 return this.phoneno;
	}

	public void setPhoneno(String phoneno){
		this.phoneno = phoneno;
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

}