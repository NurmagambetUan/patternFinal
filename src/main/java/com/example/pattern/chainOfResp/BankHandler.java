package com.example.pattern.chainOfResp;


public interface BankHandler {

    void nextHandler(BankHandler handler);

    public boolean processRequest(BankAction request);
}
