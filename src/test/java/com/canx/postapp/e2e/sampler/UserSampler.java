package com.canx.postapp.e2e.sampler;

import com.canx.postapp.dto.UserDTO;

import java.util.UUID;

public final class UserSampler {
    public static UserDTO createUser() {
        return new UserDTO(UUID.randomUUID().toString().substring(0, 5), UUID.randomUUID().toString().substring(0, 5));
    }
}
