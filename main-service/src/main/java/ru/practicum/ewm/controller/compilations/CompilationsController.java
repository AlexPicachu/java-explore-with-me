package ru.practicum.ewm.controller.compilations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.compilations.CompilationDto;
import ru.practicum.ewm.service.compilations.CompilationService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/compilations")
public class CompilationsController {

    private final CompilationService compilationService;

    /**
     * Метод для поиска подборки событий
     *
     * @param pinned - только закрепленные/не закрепленные подборки
     * @param from   - количество элементов, которые нужно пропустить для формирования текущего набора
     * @param size   - количество элементов в наборе
     * @return - подборку событий в формате CompilationDto или если не найдено ни одной подборки, возвращает пустой список
     */
    @GetMapping
    public List<CompilationDto> getCompilations(
            @RequestParam(required = false) Boolean pinned,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        log.info("Получение подборок событий");
        return compilationService.getCompilations(pinned, from, size);
    }

    /**
     * Метод для подборки событий с заданным id
     *
     * @param compId - id подборки
     * @return - подборку событий в формате CompilationDto или возвращает статус код 404
     */
    @GetMapping("/{compId}")
    public CompilationDto getCompilationsById(@PathVariable long compId) {
        log.info("Получение подборки событий по его id {}", compId);
        return compilationService.getCompilationsById(compId);
    }
}
