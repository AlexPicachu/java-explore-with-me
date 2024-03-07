package ru.practicum.ewm.mapper.category;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.category.NewCategoryDto;
import ru.practicum.ewm.model.category.Category;

/**
 * класс Mapper
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryMapper {

    /**
     * метод преобразования NewCategoryDto в Category
     */
    public static Category toCategory(NewCategoryDto newCategoryDto) {
        return Category.builder()
                .name(newCategoryDto.getName())
                .build();
    }

    /**
     * метод преобразования Category в CategoryDto
     */
    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    /**
     * метод преобразования CategoryDto в Category
     */
    public static Category toCategory(CategoryDto categoryDto) {
        return Category.builder()
                .name(categoryDto.getName())
                .build();
    }
}
