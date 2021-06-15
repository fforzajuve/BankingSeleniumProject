package com.banking.enums;

public enum Gender {
	
	MALE("m"),
	FEMALE("f");
	
	private  Gender(String name){
		this.name = name;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public static Gender getFrom(String gender) {
		for(Gender g :  Gender.values()) {
			if(g.getName().equals(gender)) {
				return g;
			}
		}
		return null;
	}

	
	public String toString() {
		return getName();
	}

}
