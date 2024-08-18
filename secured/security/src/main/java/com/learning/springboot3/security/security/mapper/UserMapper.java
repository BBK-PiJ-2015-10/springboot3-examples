package com.learning.springboot3.security.security.mapper;

import com.learning.springboot3.security.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserMapper {

    UserDetails fromUserAccount(UserAccount userAccount);

}
