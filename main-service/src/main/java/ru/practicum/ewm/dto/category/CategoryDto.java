package ru.practicum.ewm.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    //Идентификатор категории
    private Long id;
    //Название категории
    @Size(min = 1, max = 50)
    private String name;
}
