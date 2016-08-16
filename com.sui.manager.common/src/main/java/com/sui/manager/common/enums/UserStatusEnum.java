package com.sui.manager.common.enums;

public enum UserStatusEnum {

	
	ON_LINE(1, "在职"),
	OFF_LINE(2, "离职");
	
	private int type;
	
	private String name;//类型名称
	
	
	UserStatusEnum(int type, String name){
		this.type = type;
		this.name = name;
	}
	
	
	public static UserStatusEnum getByType(int type) {
		for(UserStatusEnum userStatus : UserStatusEnum.values()){
			if(type == userStatus.getType()){
				return userStatus;
			}
		}
		return null;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
