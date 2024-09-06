package com.dzo.test_bank.persistence.dto;

import com.dzo.test_bank.persistence.model.OperationJpa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OperationDto {
    public final AccountDto sourceAccount;
    public final AccountDto targetAccount;
    public final OperationJpa operationJpa;

    public static OperationDto from(OperationJpa operationJpa, AccountDto sourceAccount, AccountDto targetAccount) {
        return OperationDto.builder()
                .sourceAccount(sourceAccount)
                .targetAccount(targetAccount)
                .operationJpa(operationJpa)
                .build();
    }
}
