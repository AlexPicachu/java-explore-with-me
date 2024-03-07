package ru.practicum.ewm.service.request;

import ru.practicum.ewm.dto.request.ParticipationRequestDto;

import java.util.List;

/**
 * контракт для реализации класса RequestServiceImpl
 */
public interface RequestService {
    /**
     * Получение информации о заявках текущего пользователя на участие в чужих событиях
     *
     * @param userId - id текущего пользователя
     * @return - Запросы на участие
     */
    List<ParticipationRequestDto> getUserRequests(long userId);

    /**
     * Добавление запроса от текущего пользователя на участие в событии
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @return - Добавленный запрос в формате ParticipationRequestDto
     */
    ParticipationRequestDto addParticipationRequest(long userId, long eventId);

    /**
     * Отмена своего запроса на участие в событии
     *
     * @param userId    - id текущего пользователя
     * @param requestId - id запроса на участие
     * @return - запрос в формате ParticipationRequestDto с измененным статусом на ParticipationState.CANCELED
     */
    ParticipationRequestDto cancelRequest(long userId, long requestId);
}
