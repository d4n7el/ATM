package com.dzo.test_bank.projection;


public interface AccountProjection {
    Integer getAccountId();
    String getAccountName();
    String getAccountNum();
    Double getCurrentBalance();
    Double getPreviousBalance();
}
