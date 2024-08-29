package com.dzo.test_bank.model.entity;

import com.dzo.test_bank.model.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    Integer operationId;

    @Column(name = "source_account_id", nullable = false)
    Integer sourceAccountId;

    @Column(name = "target_account_id")
    Integer targetAccountId;

    @Column(name = "create_by_user_id", nullable = false)
    Integer createByUserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    TransactionType transactionType;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Min(0)
    @Column(name = "transaction_amount", nullable = false)
    private Double transactionAmount;

    @Min(0)
    @Column(name = "previous_balance", nullable = false)
    private Double previousBalance;

    @Min(0)
    @Column(name = "final_balance", nullable = false)
    private Double finalBalance;

    @PreUpdate
    public void preUpdate() {
        validateFinalBalance();
    }

    private void validateFinalBalance() {
        if(finalBalance < 0){
            throw new IllegalStateException("overdrafts_on_the_account ");
        }
    }

    public static Operation toDebit(Operation operation, Account account) {
        return Operation.builder()
                .sourceAccountId(operation.getSourceAccountId())
                .targetAccountId(operation.getTargetAccountId())
                .createByUserId(operation.getCreateByUserId())
                .createByUserId(operation.getCreateByUserId())
                .transactionType(operation.getTransactionType())
                .transactionAmount(operation.getTransactionAmount())
                .previousBalance(account.getPreviousBalance())
                .finalBalance(account.getCurrentBalance())
                .createdAt(new Date())
                .build();
    }

    public static Operation toCredit(Operation operation, Account account) {
        return Operation.builder()
                .sourceAccountId(operation.getSourceAccountId ())
                .targetAccountId(operation.getTargetAccountId())
                .createByUserId(operation.getCreateByUserId())
                .createByUserId(operation.getCreateByUserId())
                .transactionType(TransactionType.DEPOSIT)
                .transactionAmount(operation.getTransactionAmount())
                .previousBalance(account.getPreviousBalance())
                .finalBalance(account.getCurrentBalance())
                .createdAt(new Date())
                .build();
    }

}
