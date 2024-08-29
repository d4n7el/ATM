package com.dzo.test_bank.model.dto;

import com.dzo.test_bank.model.entity.Operation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OperationDto {
    public AccountDto sourceAccount;
    public AccountDto targetAccount;
    public Operation operation;

    public static OperationDto from(Operation operation, AccountDto sourceAccount, AccountDto targetAccount) {
        return OperationDto.builder()
                .sourceAccount(sourceAccount)
                .targetAccount(targetAccount)
                .operation(operation)
                .build();
    }
}
