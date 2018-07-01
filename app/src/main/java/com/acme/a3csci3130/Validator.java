package com.acme.a3csci3130;

public class Validator{
	// fields

	// constructor method
	public Validator(){
	}

	public boolean validate(String bn,
							String n,
							String pb,
							String a,
							String p){

		return (validate_business_number(bn)
				&& validate_name(n)
				&& validate_primary_business(pb)
				&& validate_address(a)
				&& validate_province(p));
	}

	public boolean validate_business_number(String s){
		if(s.length() == 9){
			for (char c: s.toCharArray()){
				if (c < 48 || c > 57){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public boolean validate_name(String s){
		if (s.length() > 1 && s.length() < 49) {
			return true;
		}
		return false;
	}

	public boolean validate_primary_business(String s){
		String[] al = {"Fisher", "Distributor", "Processor", "Fish Monger"};
		for (String x : al) {
			if (s.equals(x)){
				return true;
			}
		}
		return false;
	}

	public boolean validate_address(String s){
		if (s.length() > 1 && s.length() < 51) {
			return true;
		}
		return false;
	}

	public boolean validate_province(String s){
		String[] al = {"AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK",
				"YT", ""};
		for (String x : al) {
			if (s.equals(x)){
				return true;
			}
		}
		return false;
	}

}