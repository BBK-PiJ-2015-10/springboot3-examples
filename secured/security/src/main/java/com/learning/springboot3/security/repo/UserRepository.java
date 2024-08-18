package com.learning.springboot3.security.repo;

import com.learning.springboot3.security.entity.UserAccount;
import org.springframework.data.repository.Repository;


public interface UserRepository extends Repository<UserAccount,Long> {

    UserAccount findByUsername(String userName);

}
