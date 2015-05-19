package cn.futuremove.adminportal.model.joyMove;

public class MbkImage{

	private Long id;
	private String image;
	private String userid;
	private String content;
	private java.sql.Timestamp typetime;

	public Long getId(){
		 return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getImage(){
		 return this.image;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getUserid(){
		 return this.userid;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getContent(){
		 return this.content;
	}

	public void setContent(String content){
		this.content = content;
	}

	public java.sql.Timestamp getTypetime(){
		 return this.typetime;
	}

	public void setTypetime(java.sql.Timestamp typetime){
		this.typetime = typetime;
	}

}