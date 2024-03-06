package ru.practicum.ewm.service.compilations;

import ru.practicum.ewm.dto.compilations.CompilationDto;
import ru.practicum.ewm.dto.compilations.NewCompilationDto;
import ru.practicum.ewm.dto.compilations.UpdateCompilationRequest;

import java.util.List;

/**
 * контракт для реализации CompilationServiceImpl
 */
public interface CompilationService {

    /**
     * Добавление новой подборки (подборка может не содержать событий)
     *
     * @param newCompilationDto - Compilation в формате NewCompilationDto
     * @return - добавленную подборку в формате CompilationDto
     */
    CompilationDto saveCompilation(NewCompilationDto newCompilationDto);

    /**
     * Удаление подборки
     *
     * @param compId - id подборки
     */
    void deleteCompilation(long compId);

    /**
     * Обновить информацию о подборке
     *
     * @param compId                   - id подборки
     * @param updateCompilationRequest - Compilation  в формате updateCompilationRequest
     * @return - обновленная подборка в формате CompilationDto
     */
    CompilationDto updateCompilation(long compId, UpdateCompilationRequest updateCompilationRequest);

    /**
     * Метод для поиска подборки событий
     *
     * @param pinned - только закрепленные/не закрепленные подборки
     * @param from   - количество элементов, которые нужно пропустить для формирования текущего набора
     * @param size   - количество элементов в наборе
     * @return - подборку событий в формате CompilationDto или если не найдено ни одной подборки, возвращает пустой список
     */
    List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size);

    /**
     * Метод для подборки событий с заданным id
     *
     * @param compId - id подборки
     * @return - подборку событий в формате CompilationDto или возвращает статус код 404
     */
    CompilationDto getCompilationsById(long compId);
}
