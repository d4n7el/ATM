package com.dzo.test_bank.model.dto;

import com.dzo.test_bank.model.entity.User;
import com.dzo.test_bank.projection.AccountProjection;
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

    public static AccountsByUserDto from(List<AccountProjection> accounts, User user) {
        return AccountsByUserDto.builder()
               .accounts(AccountDto.from(accounts))
               .user(UserDto.from(user))
               .build();
    }

    public static AccountsByUserDto from(List<AccountProjection> accounts, UserDto user) {
        return AccountsByUserDto.builder()
                .accounts(AccountDto.from(accounts))
                .user(user)
                .build();
    }
}
