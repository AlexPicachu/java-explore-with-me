package ru.practicum.ewm.dto.compilations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.dto.event.EventShortDto;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompilationDto {
    //Список событий входящих в подборку
    private List<EventShortDto> events;
    //Идентификатор
    private Long id;
    //Закреплена ли подборка на главной странице сайта
    private Boolean pinned;
    //Заголовок подборки
    private String title;
}
