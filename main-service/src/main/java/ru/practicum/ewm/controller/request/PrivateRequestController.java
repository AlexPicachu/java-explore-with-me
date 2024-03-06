package ru.practicum.ewm.controller.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;
import ru.practicum.ewm.service.request.RequestService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/users/{userId}/requests")
public class PrivateRequestController {
    private final RequestService requestService;

    /**
     * Получение информации о заявках текущего пользователя на участие в чужих событиях
     *
     * @param userId - id текущего пользователя
     * @return - Запросы на участие
     */
    @GetMapping
    public List<ParticipationRequestDto> getUserRequests(@PathVariable long userId) {
        return requestService.getUserRequests(userId);
    }

    /**
     * Добавление запроса от текущего пользователя на участие в событии
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @return - Добавленный запрос в формате ParticipationRequestDto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto addParticipationRequest(@PathVariable long userId, @RequestParam long eventId) {
        return requestService.addParticipationRequest(userId, eventId);
    }

    /**
     * Отмена своего запроса на участие в событии
     *
     * @param userId    - id текущего пользователя
     * @param requestId - id запроса на участие
     * @return - запрос в формате ParticipationRequestDto с измененным статусом на ParticipationState.CANCELED
     */
    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable long userId, @PathVariable long requestId) {
        return requestService.cancelRequest(userId, requestId);
    }
}
