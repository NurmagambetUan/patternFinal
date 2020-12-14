package com.example.pattern.chainOfResp;

public class Invest implements BankHandler {
    protected BankHandler bankHandler;
    @Override
    public void nextHandler(BankHandler handler) {
        this.bankHandler = handler;
    }
    @Override
    public boolean processRequest(BankAction request) {
        if ("invest".equalsIgnoreCase(request.getAction())) {
            System.out.println("Make invest for your account");
            return true;
        } else {
            return false;
        }
    }
}
