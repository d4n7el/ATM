package com.dzo.test_bank.persistence.dto;

import com.dzo.test_bank.persistence.model.UserJpa;
import com.dzo.test_bank.persistence.repository.projection.AccountProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class AccountsByUserDto {
    private List<AccountDto> accounts;
    private UserDto user;

    public static AccountsByUserDto from(List<AccountProjection> accounts, UserJpa userJpa) {
        return AccountsByUserDto.builder()
               .accounts(AccountDto.from(accounts))
               .user(UserDto.from(userJpa))
               .build();
    }

    public static AccountsByUserDto from(List<AccountProjection> accounts, UserDto user) {
        return AccountsByUserDto.builder()
                .accounts(AccountDto.from(accounts))
                .user(user)
                .build();
    }
}
