package com.learning.springboot3.security.repo;

import com.learning.springboot3.security.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository  extends JpaRepository<UserAccount,Long> {


}
