package com.binhlc.adminweb.service;

import com.binhlc.adminweb.entity.UserAdminEntity;
import com.binhlc.adminweb.repo.UserAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class AdminService implements UserDetailsService {
    @Autowired
    private UserAdminRepo userAdminRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAdminEntity user = userAdminRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername(user.getUsername())
//                .password(user.getPassword())
                .password("{bcrypt}" + user.getPassword())
                .roles(String.valueOf(user.getRole()))
                .build();
    }
}
