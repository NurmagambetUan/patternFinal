package com.example.pattern.composite;


import com.example.pattern.factory.IAccount;

import java.util.ArrayList;
import java.util.List;

public class CompositeBankIAccount implements IAccount {
	
	//Collection of child accounts.
    private List<IAccount> childIAccounts = new ArrayList<IAccount>();
    
	@Override
	public void accountType() {
		for (IAccount IAccount : childIAccounts) {
			IAccount.accountType();
        }
	}
	
	//Adds the account to the composition.
    public Object add(IAccount IAccount) {
    	childIAccounts.add(IAccount);
		return null;
	}

    //Removes the account from the composition.
    public void remove(IAccount IAccount) {
    	childIAccounts.remove(IAccount);
    }
}
