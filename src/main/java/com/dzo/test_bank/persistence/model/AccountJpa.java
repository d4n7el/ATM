package com.dzo.test_bank.persistence.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"account_name", "account_num"})
})
@ToString
public class AccountJpa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NotNull
    @Length(min = 2)
    @Length(max = 20)
    @Column(name = "account_name", nullable = false)
    private String accountName;

    @NotNull
    @Length(min = 4)
    @Length(max = 20)
    @Column(name = "account_num", nullable = false)
    private String accountNum;

    @Min(0)
    @Column(name = "current_balance")
    private Double currentBalance;

    @Min(0)
    @Column(name = "previous_balance")
    private Double previousBalance;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;

    @PreUpdate
    public void preUpdate() {
        validateCurrentBalance();
    }

    private void validateCurrentBalance() {
        if (currentBalance < 0) {
            throw new IllegalStateException("overdrafts_on_the_account ");
        }
    }
}
