package com.dzo.test_bank.persistence.repository.projection;

import java.util.Date;

public interface OperationProjection {
    Integer getOperationId();
    Integer getSourceAccountId();
    Integer getTargetAccountId();
    String getTransactionType();
    Date getCreatedAt();
    Double getTransactionAmount();
    Double getPreviousBalance();
    Double getFinalBalance();
    String getSourceAccountName();
    String getSourceAccountNum();
    String getTargetAccountName();
    String getTargetAccountNum();
    String getFirstName();
    String getLastName();
    String getEmail();
    Integer getUserId();
}
