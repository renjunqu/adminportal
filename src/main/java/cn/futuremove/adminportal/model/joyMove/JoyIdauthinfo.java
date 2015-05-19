package cn.futuremove.adminportal.model.joyMove;

public class JoyIdauthinfo{

	private String mobileno;
	private byte[] idauthinfo;

	public String getMobileno(){
		 return this.mobileno;
	}

	public void setMobileno(String mobileno){
		this.mobileno = mobileno;
	}

	public byte[] getIdauthinfo(){
		 return this.idauthinfo;
	}

	public void setIdauthinfo(byte[] idauthinfo){
		this.idauthinfo = idauthinfo;
	}

}