package ru.practicum.stats.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.stats.EndpointHit;
import ru.practicum.dto.stats.ViewStats;
import ru.practicum.stats.service.StatsService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * класс контроллер для обработки запросов статистики
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    /**
     * метод добавления статистики
     *
     * @param endpointHit - принимает на вход Stats в формате EndpointHit
     */
    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void hit(@RequestBody EndpointHit endpointHit) {
        log.info("Информация сохранена {}", endpointHit);
        statsService.add(endpointHit);
    }

    /**
     * метод возвращающий статистику в определенный промежуток времени,
     *
     * @param start  -Дата и время начала диапазона за который нужно выгрузить статистику
     * @param end    - Дата и время конца диапазона за который нужно выгрузить статистику
     * @param uris   - Список uri для которых нужно выгрузить статистику
     * @param unique Нужно ли учитывать только уникальные посещения (только с уникальным ip)
     * @return
     */
    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public List<ViewStats> getStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                    @RequestParam(required = false) List<String> uris,
                                    @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("Выполнен запрос на получение статистики с {} по {}", start, end);
        return statsService.getStats(start, end, uris, unique);
    }


}
