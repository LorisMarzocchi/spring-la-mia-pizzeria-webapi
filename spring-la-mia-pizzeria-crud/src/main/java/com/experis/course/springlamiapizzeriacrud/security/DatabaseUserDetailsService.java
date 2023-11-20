package com.experis.course.springlamiapizzeriacrud.security;


import com.experis.course.springlamiapizzeriacrud.model.User;
import com.experis.course.springlamiapizzeriacrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // prendo username e cerca su db uno user con quella mail
        Optional<User> loggedUser = userRepository.findByEmail(username);
        if (loggedUser.isPresent()) {
            // se c'è user con quella mail
            // creo un DatabaseUserDetails con i dati dello user
            return new DatabaseUserDetails(loggedUser.get());

        } else {
            // se non c'è user con quella mail
            throw new UsernameNotFoundException(username);
        }
    }
}
