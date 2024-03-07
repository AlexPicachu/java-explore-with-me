package ru.practicum.ewm.controller.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.user.NewUserRequest;
import ru.practicum.ewm.dto.user.UserDto;
import ru.practicum.ewm.service.user.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin/users")
public class AdminUserController {

    private final UserService userService;

    /**
     * Возвращает информацию обо всех пользователях (учитываются параметры ограничения выборки),
     * либо о конкретных (учитываются указанные идентификаторы)\n\nВ случае, если по заданным фильтрам не найдено
     * ни одного пользователя, возвращает пустой список
     *
     * @param ids  - id пользователей
     * @param from - количество элементов, которые нужно пропустить для формирования текущего набора
     * @param size - количество элементов в наборе
     * @return
     */
    @GetMapping
    public List<UserDto> getUsers(
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return userService.getUsers(ids, from, size);
    }

    /**
     * Регистрация нового пользователя
     *
     * @param newUserRequest - Данные добавляемого пользователя
     * @return нового пользователя в формате UserDto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        log.info("Данные добавляемого пользователя {}", newUserRequest);
        return userService.registerUser(newUserRequest);
    }

    /**
     * Удаление пользователя по id
     *
     * @param userId -id пользователя
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long userId) {
        userService.delete(userId);

    }
}
