package com.canx.postapp.e2e;

import com.canx.postapp.dto.UserDTO;
import com.canx.postapp.e2e.client.UserApiClient;
import com.canx.postapp.e2e.sampler.UserSampler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;

import static com.canx.postapp.e2e.client.UserApiClient.PATH_USERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserControllerTest extends BaseTest {

    private UserApiClient userApiClient;

    @BeforeEach
    void beforeEach() {
        userApiClient = new UserApiClient(mockMvc, objectMapper);
    }

    @Test
    @DisplayName("GET " + PATH_USERS + " returns empty list")
    void getUsersReturnsEmpty() throws Exception {
        //when requesting GET /api/users
        List<UserDTO> users = userApiClient.getUsers();

        //then should receive empty list
        assertThat(users).isEmpty();
    }

    @Test
    @DisplayName("POST " + PATH_USERS + " returns created user")
    void addUserReturnsCreated() throws Exception {
        //give user input
        UserDTO userInput = UserSampler.createUser();

        //when requesting POST /api/users with user input
        UserDTO user = userApiClient.addUser(userInput);

        //then should get created user
        assertAll(
                () -> assertThat(user.getId()).isGreaterThanOrEqualTo(0),
                () -> assertThat(user.getUsername()).isEqualTo(userInput.getUsername()),
                () -> assertThat(user.getPassword()).isNotEmpty()
        );
    }

    @Test
    @DisplayName("GET " + PATH_USERS + " returns 2 users")
    void getUsersReturnsNotEmtpy() throws Exception {
        //given 2 user inputs
        UserDTO user1 = UserSampler.createUser();
        UserDTO user2 = UserSampler.createUser();

        //when requesting POST /api/users with user inputs
        userApiClient.addUser(user1);
        userApiClient.addUser(user2);

        //and then requesting GET /api/users
        List<UserDTO> users = userApiClient.getUsers();
        List<String> usernames = users.stream().map(UserDTO::getUsername).collect(Collectors.toList());

        //then should get list of 2 users matching user inputs
        assertAll(
                () -> assertThat(users.size()).isEqualTo(2),
                () -> assertThat(usernames).contains(user1.getUsername()),
                () -> assertThat(usernames).contains(user2.getUsername())
        );
    }

    @ParameterizedTest(name = "user with {2}")
    @DisplayName("POST " + PATH_USERS + " with invalid data returns error")
    @CsvSource({"'', password1, empty username", "user1, pas, too short password"})
    void addUserReturnsErrorForInvalidData(String username, String password, String description) throws Exception {
        UserDTO user = new UserDTO(username, password);

        userApiClient.addUserRaw(user)
                .andExpect(status().isBadRequest());
    }


}

