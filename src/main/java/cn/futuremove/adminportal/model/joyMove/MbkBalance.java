package cn.futuremove.adminportal.model.joyMove;

public class MbkBalance{

	private String userid;
	private java.math.BigDecimal balance;
	private String creditcardno;
	private String creditcardexpmonth;
	private String creditcardexpyear;
	private String creditcardchknum;

	public String getUserid(){
		 return this.userid;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public java.math.BigDecimal getBalance(){
		 return this.balance;
	}

	public void setBalance(java.math.BigDecimal balance){
		this.balance = balance;
	}

	public String getCreditcardno(){
		 return this.creditcardno;
	}

	public void setCreditcardno(String creditcardno){
		this.creditcardno = creditcardno;
	}

	public String getCreditcardexpmonth(){
		 return this.creditcardexpmonth;
	}

	public void setCreditcardexpmonth(String creditcardexpmonth){
		this.creditcardexpmonth = creditcardexpmonth;
	}

	public String getCreditcardexpyear(){
		 return this.creditcardexpyear;
	}

	public void setCreditcardexpyear(String creditcardexpyear){
		this.creditcardexpyear = creditcardexpyear;
	}

	public String getCreditcardchknum(){
		 return this.creditcardchknum;
	}

	public void setCreditcardchknum(String creditcardchknum){
		this.creditcardchknum = creditcardchknum;
	}

}