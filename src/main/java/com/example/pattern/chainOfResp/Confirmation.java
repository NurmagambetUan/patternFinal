package com.example.pattern.chainOfResp;

public class Confirmation implements BankHandler {
    protected BankHandler bankHandler;
    @Override
    public void nextHandler(BankHandler handler) {
        this.bankHandler = handler;
    }
    @Override
    public boolean processRequest(BankAction request) {
        if ("confirm".equalsIgnoreCase(request.getAction())) {
            System.out.println("Your invest has been invested successfully");
            return true;
        } else {
            System.out.println("Request cannot be processed by any one of handlers");
            return false;
        }
    }
}
