package ru.practicum.ewm.controller.compilations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.compilations.CompilationDto;
import ru.practicum.ewm.dto.compilations.NewCompilationDto;
import ru.practicum.ewm.dto.compilations.UpdateCompilationRequest;
import ru.practicum.ewm.service.compilations.CompilationService;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class AdminCompilationsController {

    private final CompilationService compilationService;

    /**
     * Добавление новой подборки (подборка может не содержать событий)
     *
     * @param newCompilationDto - Compilation в формате NewCompilationDto
     * @return - добавленную подборку в формате CompilationDto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto saveCompilation(@RequestBody @Valid NewCompilationDto newCompilationDto) {
        log.info("данные новой подборки {}", newCompilationDto);
        return compilationService.saveCompilation(newCompilationDto);
    }

    /**
     * Удаление подборки
     *
     * @param compId - id подборки
     */
    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable long compId) {
        log.info("Удаление подборки по id {}", compId);
        compilationService.deleteCompilation(compId);
    }

    /**
     * Обновить информацию о подборке
     *
     * @param compId                   - id подборки
     * @param updateCompilationRequest - Compilation  в формате updateCompilationRequest
     * @return - обновленная подборка в формате CompilationDto
     */
    @PatchMapping("/{compId}")
    public CompilationDto updateCompilation(@PathVariable long compId,
                                            @RequestBody @Valid UpdateCompilationRequest updateCompilationRequest) {
        log.info("данные для обновления подборки {}", updateCompilationRequest);
        return compilationService.updateCompilation(compId, updateCompilationRequest);
    }
}
