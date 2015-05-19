package cn.futuremove.adminportal.model.joyMove;

public class JoyInterpoi{

	private Long id;
	private String title;
	private String name;
	private java.math.BigDecimal latitude;
	private java.math.BigDecimal longitude;

	public Long getId(){
		 return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getTitle(){
		 return this.title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getName(){
		 return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public java.math.BigDecimal getLatitude(){
		 return this.latitude;
	}

	public void setLatitude(java.math.BigDecimal latitude){
		this.latitude = latitude;
	}

	public java.math.BigDecimal getLongitude(){
		 return this.longitude;
	}

	public void setLongitude(java.math.BigDecimal longitude){
		this.longitude = longitude;
	}

}