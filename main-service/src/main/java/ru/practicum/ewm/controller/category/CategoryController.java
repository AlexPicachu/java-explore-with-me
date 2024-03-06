package ru.practicum.ewm.controller.category;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.service.category.CategoryService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * Метод для получения категорий(список)
     *
     * @param from - количество категорий, которые нужно пропустить для формирования текущего набора
     * @param size - количество категорий в наборе
     * @return - список категорий в формате CategoryDto
     */
    @GetMapping
    public List<CategoryDto> getCategories(@RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
                                           @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return categoryService.getCategories(from, size);
    }

    /**
     * Метод возвращает Category по id
     *
     * @param catId - id категории
     * @return - Category в формате CategoryDto
     */
    @GetMapping("/{catId}")
    public CategoryDto getCategory(@PathVariable long catId) {
        return categoryService.getCategory(catId);
    }

}
