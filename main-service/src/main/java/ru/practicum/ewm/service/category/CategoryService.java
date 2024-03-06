package ru.practicum.ewm.service.category;

import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.category.NewCategoryDto;

import java.util.List;

/**
 * контракт для реализации CategoryServiceImpl
 */
public interface CategoryService {
    /**
     * Добавление новой категории
     *
     * @param newCategoryDto - Category в формате NewCategoryDto
     * @return - добавленную категорию в формате CategoryDto
     */
    CategoryDto addCategory(NewCategoryDto newCategoryDto);

    /**
     * Удаление категории
     *
     * @param catId - категории для уделения
     */
    void deleteCategory(long catId);

    /**
     * метод обновления категории
     *
     * @param catId          - категории которую нужно обновить
     * @param newCategoryDto - данные Category которые нужно обновить
     * @return - обновленную Category в формате CategoryDto
     */
    CategoryDto updateCategory(long catId, NewCategoryDto newCategoryDto);

    /**
     * Метод для получения категорий(список)
     *
     * @param from - количество категорий, которые нужно пропустить для формирования текущего набора
     * @param size - количество категорий в наборе
     * @return - список категорий в формате CategoryDto
     */
    List<CategoryDto> getCategories(Integer from, Integer size);

    /**
     * Метод возвращает Category по id
     *
     * @param catId - id категории
     * @return - Category в формате CategoryDto
     */
    CategoryDto getCategory(long catId);
}
