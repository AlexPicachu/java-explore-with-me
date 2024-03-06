package ru.practicum.ewm.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.ewm.dto.user.NewUserRequest;
import ru.practicum.ewm.dto.user.UserDto;
import ru.practicum.ewm.service.user.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminUserController.class)
class AdminUserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    @SneakyThrows
    @Test
    void registerUser() {
        NewUserRequest user = NewUserRequest.builder()
                .email("alex@yandex.ru")
                .name("alex")
                .build();
        when(userService.registerUser(user))
                .thenReturn(UserDto.builder()
                        .id(1L)
                        .name(user.getName())
                        .email(user.getEmail())
                        .build());
        mockMvc.perform(post("/admin/users", user)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }
}