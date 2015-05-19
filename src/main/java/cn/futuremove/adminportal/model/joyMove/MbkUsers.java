package cn.futuremove.adminportal.model.joyMove;

public class MbkUsers{

	private String userid;
	private String username;
	private String userpwd;
	private String mobileno;
	private String email;
	private java.sql.Timestamp registertime;
	private String userimage;
	private String gender;
	private String addr;
	private String age;
	private Long failedtimes;
	private java.sql.Timestamp lockedtime;
	private String pushkey;
	private String authtoken;
	private java.sql.Timestamp lastactivetime;

	public String getUserid(){
		 return this.userid;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUsername(){
		 return this.username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUserpwd(){
		 return this.userpwd;
	}

	public void setUserpwd(String userpwd){
		this.userpwd = userpwd;
	}

	public String getMobileno(){
		 return this.mobileno;
	}

	public void setMobileno(String mobileno){
		this.mobileno = mobileno;
	}

	public String getEmail(){
		 return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public java.sql.Timestamp getRegistertime(){
		 return this.registertime;
	}

	public void setRegistertime(java.sql.Timestamp registertime){
		this.registertime = registertime;
	}

	public String getUserimage(){
		 return this.userimage;
	}

	public void setUserimage(String userimage){
		this.userimage = userimage;
	}

	public String getGender(){
		 return this.gender;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getAddr(){
		 return this.addr;
	}

	public void setAddr(String addr){
		this.addr = addr;
	}

	public String getAge(){
		 return this.age;
	}

	public void setAge(String age){
		this.age = age;
	}

	public Long getFailedtimes(){
		 return this.failedtimes;
	}

	public void setFailedtimes(Long failedtimes){
		this.failedtimes = failedtimes;
	}

	public java.sql.Timestamp getLockedtime(){
		 return this.lockedtime;
	}

	public void setLockedtime(java.sql.Timestamp lockedtime){
		this.lockedtime = lockedtime;
	}

	public String getPushkey(){
		 return this.pushkey;
	}

	public void setPushkey(String pushkey){
		this.pushkey = pushkey;
	}

	public String getAuthtoken(){
		 return this.authtoken;
	}

	public void setAuthtoken(String authtoken){
		this.authtoken = authtoken;
	}

	public java.sql.Timestamp getLastactivetime(){
		 return this.lastactivetime;
	}

	public void setLastactivetime(java.sql.Timestamp lastactivetime){
		this.lastactivetime = lastactivetime;
	}

}