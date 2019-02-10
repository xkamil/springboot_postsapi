package com.canx.postapp.e2e.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public abstract class ApiClient {
    protected final MockMvc mockMvc;
    protected final ObjectMapper objectMapper;

    protected ApiClient(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


    protected ResultActions performGet(String path) throws Exception {
        return mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }

    protected ResultActions performPost(String path, Object content) throws Exception {
        return mockMvc.perform(post(path).content(toJson(content)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }

    protected String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}
