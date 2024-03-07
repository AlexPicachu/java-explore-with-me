package ru.practicum.ewm.controller.category;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.category.NewCategoryDto;
import ru.practicum.ewm.service.category.CategoryService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
@AllArgsConstructor
public class AdminCategoryController {
    private final CategoryService categoryService;

    /**
     * Добавление новой категории
     *
     * @param newCategoryDto - Category в формате NewCategoryDto
     * @return - добавленную категорию в формате CategoryDto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        log.info("Данные добавляемой категории {}", newCategoryDto);
        return categoryService.addCategory(newCategoryDto);
    }

    /**
     * Удаление категории
     *
     * @param catId - категории для уделения
     */
    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long catId) {
        log.info("Переданный id - {} для удаления категории", catId);
        categoryService.deleteCategory(catId);
    }

    /**
     * Изменение категории
     *
     * @param catId          - id категории
     * @param newCategoryDto - данные Category которые нужно обновить
     * @return - обновленную Category в формате CategoryDto
     */
    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@PathVariable long catId, @RequestBody @Valid NewCategoryDto newCategoryDto) {
        log.info("Данные обновляемой категории {}", newCategoryDto);
        return categoryService.updateCategory(catId, newCategoryDto);
    }


}
