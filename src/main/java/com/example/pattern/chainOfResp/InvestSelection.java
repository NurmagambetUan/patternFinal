package com.example.pattern.chainOfResp;

public class InvestSelection implements BankHandler {
    protected BankHandler bankHandler;
    @Override
    public void nextHandler(BankHandler bankHandler) {
        this.bankHandler = bankHandler;
    }
    @Override
    public boolean processRequest(BankAction request) {
        if ("selectprod".equalsIgnoreCase(request.getAction())) {
            System.out.println("Select a loan you would like to invest");
            return true;
        } else {
            bankHandler.processRequest(request);
            return false;
        }
    }
}
