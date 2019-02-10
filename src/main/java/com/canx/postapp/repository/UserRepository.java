package com.canx.postapp.repository;

import com.canx.postapp.dto.UserDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserDTO, Long> {
    Optional<UserDTO> findByUsername(String username);
}
