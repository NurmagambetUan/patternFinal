package com.example.pattern.builder;

public interface LoanBuilder {
	void loanApply();
	void loanApproval();
	void loanSanction();
	Loan loanDisburse();
}
