package com.HCLJP.taskmanager.service;

import com.HCLJP.taskmanager.dto.UserDto;
import com.HCLJP.taskmanager.entity.User;
import com.HCLJP.taskmanager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void registerAccount(UserDto userDto) {

        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .username(userDto.getUsername())
                .build();

        userRepository.save(user);
    }

    public boolean usernameExists(String username){
        return userRepository.existsByUsername(username);
    }
}
