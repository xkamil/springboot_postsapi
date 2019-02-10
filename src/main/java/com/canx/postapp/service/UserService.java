package com.canx.postapp.service;

import com.canx.postapp.configuration.UserDetail;
import com.canx.postapp.dto.UserDTO;
import com.canx.postapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO addUser(UserDTO user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<UserDTO> getUsers(){
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String errorMessage = String.format("User %s not found", username);
        Optional<UserDTO> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return new UserDetail(user.get());
        } else {
            throw new UsernameNotFoundException(errorMessage);
        }
    }
}
