package ru.practicum.ewm.mapper.user;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.dto.user.NewUserRequest;
import ru.practicum.ewm.dto.user.UserDto;
import ru.practicum.ewm.dto.user.UserShortDto;
import ru.practicum.ewm.model.user.User;

/**
 * класс Mapper
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    /**
     * Метод преобразования входящих параметров в UserShortDto
     */
    public static UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    /**
     * Метод преобразования входящих параметров в UserDto
     */
    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    /**
     * Метод преобразования входящих параметров в User
     */
    public static User toUser(NewUserRequest userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .build();
    }

}
