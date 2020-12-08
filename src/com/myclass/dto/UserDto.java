package com.myclass.dto;

public class UserDto {
	private int id;
	private String email;
	private String password;
	private String fullname;
	private String avatar;
	private String roleDec;
	private String roleName;
	
	public UserDto() {}

	public UserDto(int id, String email, String password, String fullname, String avatar, String roleDec) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.roleDec = roleDec;
	}
	public UserDto(int id, String email, String fullname, String avatar, String roleDec) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.avatar = avatar;
		this.roleDec = roleDec;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRoleDec() {
		return roleDec;
	}

	public void setRoleDec(String roleDec) {
		this.roleDec = roleDec;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
