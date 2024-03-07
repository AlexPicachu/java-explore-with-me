package ru.practicum.ewm.service.event;

import ru.practicum.ewm.dto.event.*;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;
import ru.practicum.ewm.enums.EventState;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * контракт для реализации EventServiceImpl
 */
public interface EventService {
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
    List<EventFullDto> getEvents_2(List<Long> users, List<EventState> states, List<Long> categories,
                                   LocalDateTime rangeStart, LocalDateTime rangeEnd, int from, int size);

    /**
     * метод редактирование данных любого события администратором
     *
     * @param eventId                 - id события
     * @param updateEventAdminRequest - Данные для изменения информации о событии
     * @return - EventFullDto
     */
    EventFullDto updateEvent_1(long eventId, UpdateEventAdminRequest updateEventAdminRequest);

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
    List<EventShortDto> getEvents_1(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                    LocalDateTime rangeEnd, Boolean onlyAvailable, String sort, int from, int size,
                                    HttpServletRequest httpServletRequest);

    /**
     * метод получения подробной информации об опубликованном событии по его идентификатору
     *
     * @param id                 - id события
     * @param httpServletRequest -  информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
     * @return - информацию об опубликованном событии в формате EventFullDto
     */
    EventFullDto getEvent_1(long id, HttpServletRequest httpServletRequest);

    /**
     * метод для получения событий, добавленных текущим пользователем
     *
     * @param userId - id текущего пользователя
     * @param from   - количество элементов, которые нужно пропустить для формирования текущего набора
     * @param size   - количество элементов в наборе
     * @return
     */
    List<EventShortDto> getListOfCurrentUserEvents(long userId, Integer from, Integer size);

    /**
     * Добавление нового события
     *
     * @param userId      - id текущего пользователя
     * @param newEventDto - данные добавляемого события
     * @return - добавленное событие в формате EventFullDto
     */
    EventFullDto addEvent(long userId, NewEventDto newEventDto);

    /**
     * Получение полной информации о событии добавленном текущим пользователем
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @return - полная информация о событии в формате EventFullDto
     */
    EventFullDto getEventById(long userId, long eventId);

    /**
     * Изменение события добавленного текущим пользователем
     *
     * @param userId                 - id текущего пользователя
     * @param eventId                - id редактируемого события
     * @param updateEventUserRequest - Новые данные события
     * @return - измененное событие в формате EventFullDto
     */
    EventFullDto updateEvent(long userId, long eventId, UpdateEventUserRequest updateEventUserRequest);

    /**
     * Получение информации о запросах на участие в событии текущего пользователя
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @return - запросы на участие в формате ParticipationRequestDto
     */
    List<ParticipationRequestDto> getEventParticipants(long userId, long eventId);

    /**
     * Изменение статуса (подтверждена, отменена) заявок на участие в событии текущего пользователя
     *
     * @param userId       - id текущего пользователя
     * @param eventId      - id события текущего пользователя
     * @param eventRequest - Новый статус для заявок на участие в событии текущего пользователя
     * @return - Измененный статус (подтверждена, отменена) заявки на участие
     */
    EventRequestStatusUpdateResult changeRequestStatus(long userId, long eventId,
                                                       EventRequestStatusUpdateRequest eventRequest);
}
