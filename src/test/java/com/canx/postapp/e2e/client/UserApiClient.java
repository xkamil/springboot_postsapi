package com.canx.postapp.e2e.client;

import com.canx.postapp.dto.UserDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserApiClient extends ApiClient {
    public static final String PATH_USERS = "/api/users";


    public UserApiClient(MockMvc mockMvc, ObjectMapper objectMapper) {
        super(mockMvc, objectMapper);
    }

    public List<UserDTO> getUsers() throws Exception {
        String content = getUsersRaw()
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();


        return objectMapper.readValue(content, new TypeReference<List<UserDTO>>() {
        });
    }

    public UserDTO addUser(UserDTO user) throws Exception {
        String content = addUserRaw(user)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        return objectMapper.readValue(content, UserDTO.class);
    }

    public ResultActions getUsersRaw() throws Exception {
        return performGet(PATH_USERS);
    }

    public ResultActions addUserRaw(UserDTO user) throws Exception {
        return performPost(PATH_USERS, user);
    }

}
