package ru.practicum.ewm.controller.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.dto.event.NewEventDto;
import ru.practicum.ewm.dto.event.UpdateEventUserRequest;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;
import ru.practicum.ewm.service.event.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users/{userId}/events")
public class PrivateEventController {
    private final EventService eventService;

    /**
     * метод для получения событий, добавленных текущим пользователем
     *
     * @param userId - id текущего пользователя
     * @param from   - количество элементов, которые нужно пропустить для формирования текущего набора
     * @param size   - количество элементов в наборе
     * @return
     */
    @GetMapping
    public List<EventShortDto> getListOfCurrentUserEvents(@PathVariable long userId,
                                                          @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
                                                          @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return eventService.getListOfCurrentUserEvents(userId, from, size);
    }

    /**
     * Добавление нового события
     *
     * @param userId      - id текущего пользователя
     * @param newEventDto - данные добавляемого события
     * @return - добавленное событие в формате EventFullDto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto addEvent(@PathVariable long userId, @RequestBody @Valid NewEventDto newEventDto) {
        log.info("данные добавляемого события {}", newEventDto);
        return eventService.addEvent(userId, newEventDto);
    }

    /**
     * Получение полной информации о событии добавленном текущим пользователем
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @return - полная информация о событии в формате EventFullDto
     */
    @GetMapping("/{eventId}")
    public EventFullDto getEventById(@PathVariable long userId, @PathVariable long eventId) {
        return eventService.getEventById(userId, eventId);

    }

    /**
     * Изменение события добавленного текущим пользователем
     *
     * @param userId                 - id текущего пользователя
     * @param eventId                - id редактируемого события
     * @param updateEventUserRequest - Новые данные события
     * @return - измененное событие в формате EventFullDto
     */
    @PatchMapping("/{eventId}")
    public EventFullDto updateEvent(@PathVariable long userId, @PathVariable long eventId,
                                    @RequestBody @Valid UpdateEventUserRequest updateEventUserRequest) {
        log.info("Новые данные события, {}", updateEventUserRequest);
        return eventService.updateEvent(userId, eventId, updateEventUserRequest);
    }

    /**
     * Получение информации о запросах на участие в событии текущего пользователя
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @return - запросы на участие в формате ParticipationRequestDto
     */
    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getEventParticipants(@PathVariable long userId, @PathVariable long eventId) {
        return eventService.getEventParticipants(userId, eventId);
    }

    /**
     * Изменение статуса (подтверждена, отменена) заявок на участие в событии текущего пользователя
     *
     * @param userId       - id текущего пользователя
     * @param eventId      - id события текущего пользователя
     * @param eventRequest - Новый статус для заявок на участие в событии текущего пользователя
     * @return - Измененный статус (подтверждена, отменена) заявки на участие
     */
    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult changeRequestStatus(@PathVariable long userId, @PathVariable long eventId,
                                                              @RequestBody(required = false) EventRequestStatusUpdateRequest eventRequest) {
        log.info("Новый статус для заявок на участие в событии текущего пользователя {}", eventRequest);
        return eventService.changeRequestStatus(userId, eventId, eventRequest);
    }
}
