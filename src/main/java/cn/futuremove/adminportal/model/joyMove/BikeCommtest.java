package cn.futuremove.adminportal.model.joyMove;

public class BikeCommtest{

	private String receivedstring;
	private Long commid;
	private java.sql.Timestamp recedate;
	private String ipaddr;
	private String msgtype;
	private Long msgstatus;

	public String getReceivedstring(){
		 return this.receivedstring;
	}

	public void setReceivedstring(String receivedstring){
		this.receivedstring = receivedstring;
	}

	public Long getCommid(){
		 return this.commid;
	}

	public void setCommid(Long commid){
		this.commid = commid;
	}

	public java.sql.Timestamp getRecedate(){
		 return this.recedate;
	}

	public void setRecedate(java.sql.Timestamp recedate){
		this.recedate = recedate;
	}

	public String getIpaddr(){
		 return this.ipaddr;
	}

	public void setIpaddr(String ipaddr){
		this.ipaddr = ipaddr;
	}

	public String getMsgtype(){
		 return this.msgtype;
	}

	public void setMsgtype(String msgtype){
		this.msgtype = msgtype;
	}

	public Long getMsgstatus(){
		 return this.msgstatus;
	}

	public void setMsgstatus(Long msgstatus){
		this.msgstatus = msgstatus;
	}

}