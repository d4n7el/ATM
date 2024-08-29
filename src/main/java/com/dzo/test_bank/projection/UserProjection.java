package com.dzo.test_bank.projection;

import java.util.Date;

public interface UserProjection {
    Integer getUserId();
    String getFirstName();
    String getLastName();
    String getEmail();
    Date getDateOfBirth();
    Integer getAccountCount();
}
