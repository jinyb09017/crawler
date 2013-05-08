package com.jinyb.crawler.entity;
// default package



/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String pwd;
     private Integer level;//级别，1是超级用户，其它是一般用户吧
     private Integer state;//1是激活状态，2是非激活状态
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(Integer id, String name, String pwd, Integer level,
			Integer state) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.level = level;
		this.state = state;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", pwd=" + pwd
				+ ", level=" + level + ", state=" + state + "]";
	}

   
}