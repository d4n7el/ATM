package com.dzo.test_bank.persistence.dto;

import com.dzo.test_bank.persistence.model.UserJpa;
import com.dzo.test_bank.persistence.repository.projection.UserProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBasicDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;

    public static UserBasicDto from(UserProjection user) {
        return UserBasicDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public static List<UserBasicDto> from(List<UserProjection> users) {
        return users.stream()
                .map(UserBasicDto::from)
                .toList();
    }

    public static UserBasicDto from(UserJpa userJpa) {
        return UserBasicDto.builder()
                .userId(userJpa.getUserId())
                .firstName(userJpa.getFirstName())
                .lastName(userJpa.getLastName())
                .email(userJpa.getEmail())
                .build();
    }

    public static UserBasicDto from(Integer userId, String firstName, String lastName, String email) {
        return UserBasicDto.builder()
                .userId(userId)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }
}
