package cn.futuremove.adminportal.model.joyMove;

import cn.futuremove.adminportal.core.support.ExtJSBaseParameter;

public class JoyCar extends ExtJSBaseParameter {

	private Long id;
	private java.math.BigDecimal positionx;
	private java.math.BigDecimal positiony;
	private Long state;
	private String desp;
	private String mobileno;

	public Long getId(){
		 return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public java.math.BigDecimal getPositionx(){
		 return this.positionx;
	}

	public void setPositionx(java.math.BigDecimal positionx){
		this.positionx = positionx;
	}

	public java.math.BigDecimal getPositiony(){
		 return this.positiony;
	}

	public void setPositiony(java.math.BigDecimal positiony){
		this.positiony = positiony;
	}

	public Long getState(){
		 return this.state;
	}

	public void setState(Long state){
		this.state = state;
	}

	public String getDesp(){
		 return this.desp;
	}

	public void setDesp(String desp){
		this.desp = desp;
	}

	public String getMobileno(){
		 return this.mobileno;
	}

	public void setMobileno(String mobileno){
		this.mobileno = mobileno;
	}

}