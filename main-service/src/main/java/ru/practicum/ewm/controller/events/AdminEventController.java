package ru.practicum.ewm.controller.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.UpdateEventAdminRequest;
import ru.practicum.ewm.enums.EventState;
import ru.practicum.ewm.service.event.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/events")
@AllArgsConstructor
public class AdminEventController {
    private final EventService eventService;

    /**
     * Метод возвращает полную информацию обо всех событиях подходящих под переданные условия\n\nВ случае,
     * если по заданным фильтрам не найдено ни одного события, возвращает пустой список
     *
     * @param users      - список id пользователей, чьи события нужно найти
     * @param states     - список состояний в которых находятся искомые события
     * @param categories - список id категорий в которых будет вестись поиск
     * @param rangeStart - дата и время не раньше которых должно произойти событие
     * @param rangeEnd   - дата и время не позже которых должно произойти событие
     * @param from       - количество событий, которые нужно пропустить для формирования текущего набора
     * @param size       -количество событий в наборе
     * @return - List<EventFullDto>
     */
    @GetMapping
    public List<EventFullDto> getEvents_2(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<EventState> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero int from,
            @RequestParam(required = false, defaultValue = "10") @Positive int size
    ) {
        return eventService.getEvents_2(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    /**
     * метод редактирование данных любого события администратором
     *
     * @param eventId                 - id события
     * @param updateEventAdminRequest - Данные для изменения информации о событии
     * @return - EventFullDto
     */
    @PatchMapping("/{eventId}")
    public EventFullDto updateEvent_1(@PathVariable long eventId,
                                      @RequestBody @Valid UpdateEventAdminRequest updateEventAdminRequest) {
        log.info("Данные для обновления {}", updateEventAdminRequest);
        return eventService.updateEvent_1(eventId, updateEventAdminRequest);
    }

}
