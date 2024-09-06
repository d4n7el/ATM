package com.dzo.test_bank.persistence.repository.projection;

import java.util.Date;

public interface UserProjection {
    Integer getUserId();
    String getFirstName();
    String getLastName();
    String getEmail();
    Date getDateOfBirth();
    Integer getAccountCount();
}
