package ru.practicum.ewm.controller.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.service.event.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class PublicEventController {
    private final EventService eventService;

    /**
     * Получение событий с возможностью фильтрации
     *
     * @param text               - текст для поиска в содержимом аннотации и подробном описании события
     * @param categories         - список идентификаторов категорий в которых будет вестись поиск
     * @param paid               - поиск только платных/бесплатных событий
     * @param rangeStart         - дата и время не раньше которых должно произойти событие
     * @param rangeEnd           - дата и время не позже которых должно произойти событие
     * @param onlyAvailable      - только события у которых не исчерпан лимит запросов на участие
     * @param sort               - Вариант сортировки: по дате события или по количеству просмотров
     * @param from               - количество событий, которые нужно пропустить для формирования текущего набора
     * @param size               - количество событий в наборе
     * @param httpServletRequest - информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
     * @return список событий в формате EventShortDto
     */
    @GetMapping
    public List<EventShortDto> getEvents_1(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero int from,
            @RequestParam(required = false, defaultValue = "10") @Positive int size,
            HttpServletRequest httpServletRequest) {
        return eventService.getEvents_1(text, categories, paid, rangeStart, rangeEnd, onlyAvailable,
                sort, from, size, httpServletRequest);
    }

    /**
     * метод получения подробной информации об опубликованном событии по его идентификатору
     *
     * @param id                 - id события
     * @param httpServletRequest -  информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
     * @return - информацию об опубликованном событии в формате EventFullDto
     */
    @GetMapping("/{id}")
    public EventFullDto getEvent_1(@PathVariable long id, HttpServletRequest httpServletRequest) {
        log.info("Получение подробной информации об опубликованном событии по его идентификатору {}", id);
        return eventService.getEvent_1(id, httpServletRequest);
    }

}
