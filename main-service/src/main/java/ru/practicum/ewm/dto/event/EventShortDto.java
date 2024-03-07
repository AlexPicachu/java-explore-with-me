package ru.practicum.ewm.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.user.UserShortDto;

import java.time.LocalDateTime;

/**
 * Краткая информация о событии
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventShortDto {
    //Краткое описание
    private String annotation;
    //категория
    private CategoryDto category;
    //Количество одобренных заявок на участие в данном событии
    private Integer confirmedRequests;
    //Дата и время на которые намечено событие (в формате \"yyyy-MM-dd HH:mm:ss\")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    //Идентификатор
    private Long id;
    private UserShortDto initiator;
    //нужно ли оплачивать участие
    private boolean paid;
    //Заголовок
    private String title;
    //Количество просмотрев события
    private Integer views;
}
