package com.example.pattern.proxy;

public class RealAuthority implements Authority {
    @Override
    public String connectTo(String login)
    {
        return "User "+ login + " accessed";
    }
}
