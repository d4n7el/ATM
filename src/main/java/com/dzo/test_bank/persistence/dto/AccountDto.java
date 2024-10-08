package com.dzo.test_bank.persistence.dto;

import com.dzo.test_bank.persistence.model.AccountJpa;
import com.dzo.test_bank.persistence.repository.projection.AccountProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private Integer accountId;
    private String accountName;
    private String accountNum;
    private Double currentBalance;
    private Double previousBalance;

    public static AccountDto from(AccountProjection account) {
        AccountDto dto = new AccountDto();
        dto.setAccountId(account.getAccountId());
        dto.setAccountName(account.getAccountName());
        dto.setAccountNum(account.getAccountNum());
        dto.setCurrentBalance(account.getCurrentBalance());
        dto.setPreviousBalance(account.getPreviousBalance());
        return dto;
    }
    public static List<AccountDto> from(List<AccountProjection> accounts) {
        return accounts.stream()
                .map(AccountDto::from)
                .toList();
    }

    public static AccountDto from(AccountJpa accountJpa) {
        AccountDto dto = new AccountDto();
        dto.setAccountId(accountJpa.getAccountId());
        dto.setAccountName(accountJpa.getAccountName());
        dto.setAccountNum(accountJpa.getAccountNum());
        dto.setCurrentBalance(accountJpa.getCurrentBalance());
        dto.setPreviousBalance(accountJpa.getPreviousBalance());
        return dto;
    }
}
