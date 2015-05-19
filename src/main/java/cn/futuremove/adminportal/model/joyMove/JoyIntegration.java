package cn.futuremove.adminportal.model.joyMove;

public class JoyIntegration{

	private Long jifenid;
	private String mobileno;
	private Long jifen;
	private String jifendesc;
	private Long statusmark;
	private java.sql.Timestamp jifendate;

	public Long getJifenid(){
		 return this.jifenid;
	}

	public void setJifenid(Long jifenid){
		this.jifenid = jifenid;
	}

	public String getMobileno(){
		 return this.mobileno;
	}

	public void setMobileno(String mobileno){
		this.mobileno = mobileno;
	}

	public Long getJifen(){
		 return this.jifen;
	}

	public void setJifen(Long jifen){
		this.jifen = jifen;
	}

	public String getJifendesc(){
		 return this.jifendesc;
	}

	public void setJifendesc(String jifendesc){
		this.jifendesc = jifendesc;
	}

	public Long getStatusmark(){
		 return this.statusmark;
	}

	public void setStatusmark(Long statusmark){
		this.statusmark = statusmark;
	}

	public java.sql.Timestamp getJifendate(){
		 return this.jifendate;
	}

	public void setJifendate(java.sql.Timestamp jifendate){
		this.jifendate = jifendate;
	}

}