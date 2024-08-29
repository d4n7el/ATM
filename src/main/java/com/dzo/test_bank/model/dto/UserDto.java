package com.dzo.test_bank.model.dto;

import com.dzo.test_bank.model.entity.User;
import com.dzo.test_bank.projection.UserProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private Integer accountCount;

    public static UserDto from(UserProjection user) {
        return UserDto.builder()
            .userId(user.getUserId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .dateOfBirth(user.getDateOfBirth())
            .accountCount(user.getAccountCount())
            .build();
    }

    public static List<UserDto> from(List<UserProjection> users) {
        return users.stream()
                .map(UserDto::from)
                .toList();
    }

    public static UserDto from(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }
}
