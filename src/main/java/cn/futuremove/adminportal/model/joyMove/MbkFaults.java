package cn.futuremove.adminportal.model.joyMove;

public class MbkFaults{

	private Long id;
	private byte[] faultimage;
	private String faultdesp;
	private java.sql.Timestamp faulttime;

	public Long getId(){
		 return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public byte[] getFaultimage(){
		 return this.faultimage;
	}

	public void setFaultimage(byte[] faultimage){
		this.faultimage = faultimage;
	}

	public String getFaultdesp(){
		 return this.faultdesp;
	}

	public void setFaultdesp(String faultdesp){
		this.faultdesp = faultdesp;
	}

	public java.sql.Timestamp getFaulttime(){
		 return this.faulttime;
	}

	public void setFaulttime(java.sql.Timestamp faulttime){
		this.faulttime = faulttime;
	}

}