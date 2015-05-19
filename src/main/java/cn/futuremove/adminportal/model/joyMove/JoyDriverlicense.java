package cn.futuremove.adminportal.model.joyMove;

public class JoyDriverlicense{

	private String mobileno;
	private byte[] driverauthinfo;
	private String driverlicensenumber;
	private byte[] driverauthinfoBack;

	public String getMobileno(){
		 return this.mobileno;
	}

	public void setMobileno(String mobileno){
		this.mobileno = mobileno;
	}

	public byte[] getDriverauthinfo(){
		 return this.driverauthinfo;
	}

	public void setDriverauthinfo(byte[] driverauthinfo){
		this.driverauthinfo = driverauthinfo;
	}

	public String getDriverlicensenumber(){
		 return this.driverlicensenumber;
	}

	public void setDriverlicensenumber(String driverlicensenumber){
		this.driverlicensenumber = driverlicensenumber;
	}

	public byte[] getDriverauthinfoBack(){
		 return this.driverauthinfoBack;
	}

	public void setDriverauthinfoBack(byte[] driverauthinfoBack){
		this.driverauthinfoBack = driverauthinfoBack;
	}

}