package ru.practicum.ewm.service.user;

import ru.practicum.ewm.dto.user.NewUserRequest;
import ru.practicum.ewm.dto.user.UserDto;

import java.util.List;

/**
 * контракт для реализации класса UserServiceImpl
 */
public interface UserService {
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
    List<UserDto> getUsers(List<Long> ids, Integer from, Integer size);

    /**
     * Регистрация нового пользователя
     *
     * @param newUserRequest - Данные добавляемого пользователя
     * @return нового пользователя в формате UserDto
     */
    UserDto registerUser(NewUserRequest newUserRequest);

    /**
     * Удаление пользователя по id
     *
     * @param userId -id пользователя
     */
    void delete(long userId);
}
