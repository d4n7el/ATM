package com.dzo.test_bank.model.dto;

import com.dzo.test_bank.model.entity.Operation;
import com.dzo.test_bank.model.enums.TransactionType;
import com.dzo.test_bank.projection.AccountProjection;
import com.dzo.test_bank.projection.OperationProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OperationDetailDto {
    public String sourceAccountName;
    public String sourceAccountNum;
    public String targetAccountName;
    public String targetAccountNum;
    public Double finalBalance;
    public Double transactionAmount;
    public Double previousBalance;
    public TransactionType transactionType;
    public Integer sourceAccountId;
    public Integer targetAccountId;
    public Integer operationId;
    public Date createdAt;
    public UserBasicDto createBy;

    public static OperationDetailDto from(OperationProjection operation) {
        return OperationDetailDto.builder()
                .sourceAccountName(operation.getSourceAccountName())
                .sourceAccountNum(operation.getSourceAccountNum())
                .targetAccountName(operation.getTargetAccountName())
                .targetAccountNum(operation.getTargetAccountNum())
                .finalBalance(operation.getFinalBalance())
                .transactionAmount(operation.getTransactionAmount())
                .previousBalance(operation.getPreviousBalance())
                .transactionType(TransactionType.valueOf(operation.getTransactionType()))
                .sourceAccountId(operation.getSourceAccountId())
                .targetAccountId(operation.getTargetAccountId())
                .operationId(operation.getOperationId())
                .createdAt(operation.getCreatedAt())
                .createBy(UserBasicDto.from(operation.getUserId(), operation.getFirstName(), operation.getLastName(), operation.getEmail()))
                .build();
    }

    public static List<OperationDetailDto> from(List<OperationProjection> operations) {
        return operations.stream()
                .map(OperationDetailDto::from)
                .toList();
    }
}
