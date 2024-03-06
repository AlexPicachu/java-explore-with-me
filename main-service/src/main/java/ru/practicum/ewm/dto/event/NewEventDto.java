package ru.practicum.ewm.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ru.practicum.ewm.dto.location.LocationDto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDto {
    //Краткое описание события
    @NotBlank
    @Size(min = 20, max = 2000)
    private String annotation;
    //id категории к которой относится событие
    @NotNull
    private Long category;
    //Полное описание события
    @NotBlank
    @Size(min = 20, max = 7000)
    private String description;
    //Дата и время на которые намечено событие. Дата и время указываются в формате \"yyyy-MM-dd HH:mm:ss\"
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Future
    private LocalDateTime eventDate;
    //локация, где будет проходить событие
    @NotNull
    @Valid
    private LocationDto location;
    //Нужно ли оплачивать участие в событии
    @Nullable
    private Boolean paid = false;
    //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    @Nullable
    @PositiveOrZero
    private Integer participantLimit = 0;
    //Нужна ли пре-модерация заявок на участие. Если true, то все заявки будут ожидать подтверждения инициатором события. Если false - то будут подтверждаться автоматически.
    @Nullable
    private Boolean requestModeration = true;
    //Заголовок события
    @NotNull
    @Size(min = 3, max = 120)
    private String title;
}
