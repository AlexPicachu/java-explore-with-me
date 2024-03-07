package ru.practicum.ewm.dto.compilations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCompilationRequest {
    //Список id событий подборки для полной замены текущего списка
    private List<Long> events;
    //Закреплена ли подборка на главной странице сайта
    private Boolean pinned;
    //Заголовок подборки
    @Size(min = 1, max = 50)
    private String title;
}
