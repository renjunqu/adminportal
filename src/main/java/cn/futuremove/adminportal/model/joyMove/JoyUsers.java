package cn.futuremove.adminportal.model.joyMove;

public class JoyUsers{

	private Long id;
	private String mobileno;
	private String username;
	private String userpwd;
	private String email;
	private java.sql.Timestamp registertime;
	private String gender;
	private String addr;
	private String age;
	private Long failedtimes;
	private java.sql.Timestamp lockedtime;
	private String pushkey;
	private String authtoken;
	private Long authenticateid;
	private Long authenticatedriver;
	private java.math.BigDecimal deposit;
	private String homeaddr;
	private String corpaddr;
	private java.math.BigDecimal homelatitude;
	private java.math.BigDecimal homelongitude;
	private java.math.BigDecimal corplatitude;
	private java.math.BigDecimal corplongitude;
	private java.sql.Timestamp lastactivetime;

	public Long getId(){
		 return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getMobileno(){
		 return this.mobileno;
	}

	public void setMobileno(String mobileno){
		this.mobileno = mobileno;
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

	public Long getAuthenticateid(){
		 return this.authenticateid;
	}

	public void setAuthenticateid(Long authenticateid){
		this.authenticateid = authenticateid;
	}

	public Long getAuthenticatedriver(){
		 return this.authenticatedriver;
	}

	public void setAuthenticatedriver(Long authenticatedriver){
		this.authenticatedriver = authenticatedriver;
	}

	public java.math.BigDecimal getDeposit(){
		 return this.deposit;
	}

	public void setDeposit(java.math.BigDecimal deposit){
		this.deposit = deposit;
	}

	public String getHomeaddr(){
		 return this.homeaddr;
	}

	public void setHomeaddr(String homeaddr){
		this.homeaddr = homeaddr;
	}

	public String getCorpaddr(){
		 return this.corpaddr;
	}

	public void setCorpaddr(String corpaddr){
		this.corpaddr = corpaddr;
	}

	public java.math.BigDecimal getHomelatitude(){
		 return this.homelatitude;
	}

	public void setHomelatitude(java.math.BigDecimal homelatitude){
		this.homelatitude = homelatitude;
	}

	public java.math.BigDecimal getHomelongitude(){
		 return this.homelongitude;
	}

	public void setHomelongitude(java.math.BigDecimal homelongitude){
		this.homelongitude = homelongitude;
	}

	public java.math.BigDecimal getCorplatitude(){
		 return this.corplatitude;
	}

	public void setCorplatitude(java.math.BigDecimal corplatitude){
		this.corplatitude = corplatitude;
	}

	public java.math.BigDecimal getCorplongitude(){
		 return this.corplongitude;
	}

	public void setCorplongitude(java.math.BigDecimal corplongitude){
		this.corplongitude = corplongitude;
	}

	public java.sql.Timestamp getLastactivetime(){
		 return this.lastactivetime;
	}

	public void setLastactivetime(java.sql.Timestamp lastactivetime){
		this.lastactivetime = lastactivetime;
	}

}