package cn.futuremove.adminportal.model.joyMove;

public class JoyConfigs{

	private Long id;
	private String classname;
	private String configkey;
	private String configvalue;

	public Long getId(){
		 return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getClassname(){
		 return this.classname;
	}

	public void setClassname(String classname){
		this.classname = classname;
	}

	public String getConfigkey(){
		 return this.configkey;
	}

	public void setConfigkey(String configkey){
		this.configkey = configkey;
	}

	public String getConfigvalue(){
		 return this.configvalue;
	}

	public void setConfigvalue(String configvalue){
		this.configvalue = configvalue;
	}

}