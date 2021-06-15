package com.banking.bo;

import org.apache.commons.lang3.RandomStringUtils;

import com.banking.enums.Gender;

public class Customer {
	private String customerName;
	private Gender gender;
	private String dob;
	private String address;
	private String city;
	private String state;
	private String pin;
	private String mobileNumber;
	private String email;
	private String password;
	
	private Customer(CustomerBuilder builder) {
		customerName = builder.customerName;
		gender = builder.gender;
		dob = builder.dob;
		address = builder.address;
		city = builder.city;
		state = builder.state;
		pin = builder.pin;
		mobileNumber = builder.mobileNumber;
		email = builder.email;
		password = builder.password;
	}

	public String getCustomerName() {
		return customerName;
	}
	
	public Gender getGender() {
		return gender;
	}

	public String getDob() {
		return dob;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPin() {
		return pin;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}


	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", gender=" + gender.getName() + ", dob=" + dob + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", pin=" + pin + ", mobileNumber=" + mobileNumber + ", email="
				+ email + ", password=" + password + "]";
	}


	public static class CustomerBuilder {
		private String customerName;
		private Gender gender;
		private String dob;
		private String address;
		private String city;
		private String state;
		private String pin;
		private String mobileNumber;
		private String email;
		private String password;
		
		public  CustomerBuilder(String customerName) {
			this.customerName = customerName + RandomStringUtils.randomAlphabetic(3);
		}
		
		public CustomerBuilder gender(Gender gender) {
			this.gender = gender;
			return this;
		}
		
		
		public CustomerBuilder dob(String dob) {
			this.dob = dob;
			return this;
		}
		
		public CustomerBuilder address(String address) {
			this.address = address + " " + RandomStringUtils.randomNumeric(3);
			return this;
		}
		
		public CustomerBuilder city(String city) {
			this.city = city;
			return this;
		}
		
		public CustomerBuilder state(String state) {
			this.state = state;
			return this;
		}
		
		public CustomerBuilder pin(String pin) {
			this.pin = pin;
			return this;
		}
		
		public CustomerBuilder mobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
			return this;
		}
		
		public CustomerBuilder email(String email) {
			this.email = RandomStringUtils.randomAlphabetic(3)+email;
			return this;
		}
		
		public CustomerBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public Customer build() {
			Customer customer =  new Customer(this);
            return customer;
        }
	}


}
