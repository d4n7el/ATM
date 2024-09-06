package com.dzo.test_bank.persistence.dto;

import com.dzo.test_bank.persistence.types.TransactionType;
import com.dzo.test_bank.persistence.repository.projection.OperationProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OperationDetailDto {
    public final String sourceAccountName;
    public final String sourceAccountNum;
    public final String targetAccountName;
    public final String targetAccountNum;
    public final Double finalBalance;
    public final Double transactionAmount;
    public final Double previousBalance;
    public final TransactionType transactionType;
    public final Integer sourceAccountId;
    public final Integer targetAccountId;
    public final Integer operationId;
    public final Date createdAt;
    public final UserBasicDto createBy;

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
