package com.canx.postapp.e2e;

import com.canx.postapp.dto.UserDTO;
import com.canx.postapp.e2e.sampler.UserSampler;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class PostControllerTest extends BaseTest {

    private static UserDTO user;

    @BeforeAll
    static void beforeAll(){

    }



}

