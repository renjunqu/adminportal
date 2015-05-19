package cn.futuremove.adminportal.model.joyMove;

public class JoyMessages{

	private Long id;
	private String type;
	private String content;
	private java.sql.Timestamp createtime;
	private String mobileno;

	public Long getId(){
		 return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getType(){
		 return this.type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getContent(){
		 return this.content;
	}

	public void setContent(String content){
		this.content = content;
	}

	public java.sql.Timestamp getCreatetime(){
		 return this.createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime){
		this.createtime = createtime;
	}

	public String getMobileno(){
		 return this.mobileno;
	}

	public void setMobileno(String mobileno){
		this.mobileno = mobileno;
	}

}