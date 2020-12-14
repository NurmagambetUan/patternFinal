package com.example.pattern.factory;



public class AccountFactory {
	final String CURRENT_ACCOUNT = "CURRENT";
	final String SAVING_ACCOUNT  = "SAVING";
	//use getAccount method to get object of type Account   
	//It is factory method for object of type Account
    public IAccount getAccount(String accountType){
    	if(CURRENT_ACCOUNT.equals(accountType)) {  
    		return new CurrentIAccount();
    	}else if(SAVING_ACCOUNT.equals(accountType)){  
    		return new SavingIAccount();
    	}   
    	return null;  
    }  
}
