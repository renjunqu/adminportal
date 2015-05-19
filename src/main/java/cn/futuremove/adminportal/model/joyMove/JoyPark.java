package cn.futuremove.adminportal.model.joyMove;

public class JoyPark{

	private Long id;
	private java.math.BigDecimal positionx;
	private java.math.BigDecimal positiony;
	private String desp;

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

	public String getDesp(){
		 return this.desp;
	}

	public void setDesp(String desp){
		this.desp = desp;
	}

}