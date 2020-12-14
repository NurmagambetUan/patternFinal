package com.example.pattern.factory;

public class CurrentIAccount implements IAccount {

	@Override
	public void accountType() {
		System.out.println("CURRENT ACCOUNT");
	}

}
