package ru.practicum.ewm.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.location.LocationDto;
import ru.practicum.ewm.dto.user.UserShortDto;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventFullDto {
    //Краткое описание
    private String annotation;
    //категория
    private CategoryDto category;
    //Количество одобренных заявок на участие в данном событии
    private Integer confirmedRequests;
    //Дата и время создания события (в формате \"yyyy-MM-dd HH:mm:ss\")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
    //Полное описание события
    private String description;
    //Дата и время на которые намечено событие (в формате \"yyyy-MM-dd HH:mm:ss\")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    //Идентификатор
    private Long id;
    //Инициатор события
    private UserShortDto initiator;
    //Локация
    private LocationDto location;
    //Нужно ли оплачивать участие
    private boolean paid;
    //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    private Integer participantLimit;
    //Дата и время публикации события (в формате \"yyyy-MM-dd HH:mm:ss\")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
    //Нужна ли пре-модерация заявок на участие
    private Boolean requestModeration;
    //Список состояний жизненного цикла события
    private String state;
    //Заголовок
    private String title;
    //Количество просмотрев события
    private Integer views;
}
