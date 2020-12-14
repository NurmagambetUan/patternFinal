package com.example.pattern.chainOfResp;

public class BankAction {
    private String action;
    public BankAction(String action) {
        this.action = action;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
}
