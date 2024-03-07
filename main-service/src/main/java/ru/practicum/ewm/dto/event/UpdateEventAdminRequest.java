package ru.practicum.ewm.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.dto.location.LocationDto;
import ru.practicum.ewm.enums.AdminStateAction;

import javax.validation.constraints.Future;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventAdminRequest {
    //Новая аннотация
    @Size(min = 20, max = 2000)
    private String annotation;
    //Новая категория
    private Long category;
    //Новое описание
    @Size(min = 20, max = 7000)
    private String description;
    //Новые дата и время на которые намечено событие. Дата и время указываются в формате \"yyyy-MM-dd HH:mm:ss\"
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Future
    private LocalDateTime eventDate;
    //локация, где будет проходить событие
    private LocationDto location;
    //Новое значение флага о платности мероприятия
    private Boolean paid;
    //Новый лимит пользователей
    @PositiveOrZero
    private Integer participantLimit;
    //Нужна ли пре-модерация заявок на участие
    private Boolean requestModeration;
    //Новое состояние события
    private AdminStateAction stateAction;
    //Новый заголовок
    @Size(min = 3, max = 120)
    private String title;


}
