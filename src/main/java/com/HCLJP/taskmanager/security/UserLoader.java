package com.HCLJP.taskmanager.security;

import com.HCLJP.taskmanager.entity.User;
import com.HCLJP.taskmanager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserLoader implements UserDetailsService {

    @Autowired
    UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(s);
        user.orElseThrow(() -> new UsernameNotFoundException("User '"+s+"'"+" was not found."));
        User user1 = user.get();

        return new org.springframework.security.core.userdetails.User(user1.getUsername(),
                user1.getPassword(), new ArrayList<>());
    }
}
