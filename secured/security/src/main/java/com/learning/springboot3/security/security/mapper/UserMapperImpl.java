package com.learning.springboot3.security.security.mapper;

import com.learning.springboot3.security.entity.UserAccount;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDetails fromUserAccount(UserAccount userAccount) {
        return User.withDefaultPasswordEncoder()
                .username(userAccount.getUsername())
                .password(userAccount.getPassword())
                .authorities(userAccount.getAuthorities())
                .build();
    }
}
