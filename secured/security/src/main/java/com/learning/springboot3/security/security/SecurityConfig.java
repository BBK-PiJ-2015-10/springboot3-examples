package com.learning.springboot3.security.security;


import com.learning.springboot3.security.entity.UserAccount;
import com.learning.springboot3.security.repo.UserManagementRepository;
import com.learning.springboot3.security.repo.UserRepository;
import com.learning.springboot3.security.security.mapper.UserMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetailsManager userDetailsManager =
//                new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build()
//        );
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("password")
//                        .roles("ADMIN")
//                        .build()
//        );
//        return userDetailsManager;
//    }

    @Bean
    CommandLineRunner initUsers(UserManagementRepository repository) {
        return args -> {
            repository.save(
                    new UserAccount("user", "password", "ROLE_USER")
            );
            repository.save(
                    new UserAccount("admin", "password", "ROLE_ADMIN")
            );
        };
    }

    @Bean
    UserDetailsService userDetailsServiceViaRepo(UserRepository userRepository, UserMapper userMapper) {
        return username ->
                userMapper.fromUserAccount(userRepository.findByUsername(username));
    }

}
