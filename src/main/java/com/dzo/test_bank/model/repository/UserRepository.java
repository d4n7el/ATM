package com.dzo.test_bank.model.repository;

import com.dzo.test_bank.projection.UserProjection;
import com.dzo.test_bank.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    @Query("SELECT u.userId AS userId, u.firstName AS firstName, u.lastName AS lastName, " +
            "u.email AS email, u.dateOfBirth AS dateOfBirth, COUNT(a) AS accountCount " +
            "FROM User u LEFT JOIN Account a ON u.userId = a.userId " +
            "GROUP BY u.userId, u.firstName, u.lastName, u.email, u.dateOfBirth")
    List<UserProjection> findAllUsers();

    @Query("SELECT u.userId AS userId, u.firstName AS firstName, u.lastName AS lastName, " +
            "u.email AS email, u.dateOfBirth AS dateOfBirth, COUNT(a) AS accountCount " +
            "FROM User u LEFT JOIN Account a ON u.userId = a.userId " +
            "WHERE u.userId = :userId " +
            "GROUP BY u.userId, u.firstName, u.lastName, u.email, u.dateOfBirth")
    UserProjection findByIdUser(@Param("userId") Integer userId);
}
