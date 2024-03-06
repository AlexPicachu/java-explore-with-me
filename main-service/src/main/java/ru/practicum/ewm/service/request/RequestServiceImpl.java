package ru.practicum.ewm.service.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;
import ru.practicum.ewm.enums.EventState;
import ru.practicum.ewm.enums.ParticipationState;
import ru.practicum.ewm.exception.ConflictException;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.mapper.request.RequestMapper;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.model.request.ParticipationRequest;
import ru.practicum.ewm.model.user.User;
import ru.practicum.ewm.repository.event.EventRepository;
import ru.practicum.ewm.repository.request.RequestRepository;
import ru.practicum.ewm.repository.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * класс, реализующий логику методов интерфейса RequestService
 */
@Slf4j
@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final EventRepository eventRepository;

    /**
     * Получение информации о заявках текущего пользователя на участие в чужих событиях
     *
     * @param userId - id текущего пользователя
     * @return - Запросы на участие
     */
    @Override
    public List<ParticipationRequestDto> getUserRequests(long userId) {
        User User = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        List<ParticipationRequest> requestList = requestRepository.findAllByRequester_Id(userId);
        List<ParticipationRequestDto> requestDtos = requestList.stream().map(RequestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
        log.info("Найдены запросы на участие {}", requestList);
        return requestDtos;
    }

    /**
     * Добавление запроса от текущего пользователя на участие в событии
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @return - Добавленный запрос в формате ParticipationRequestDto
     */
    @Override
    public ParticipationRequestDto addParticipationRequest(long userId, long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие не найдено или недоступно"));
        if (userId == event.getInitiator().getId()
                || requestRepository.findByRequesterIdAndEventId(userId, eventId) != null) {
            log.info("Для этого пользователя нельзя создать запрос");
            throw new ConflictException("Нарушение целостности данных");
        }
        if (!event.getState().equals(EventState.PUBLISHED)) {
            log.info("Событие не опубликовано");
            throw new ConflictException("Нарушение целостности данных");
        }
        Integer confirmedRequests = requestRepository.findAllByEvent(event).size();
        if (confirmedRequests >= event.getParticipantLimit() && event.getParticipantLimit() > 0) {
            log.info("Достигнут лимит участников");
            throw new ConflictException("Нарушение целостности данных");
        }
        ParticipationState participationState;
        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            participationState = ParticipationState.CONFIRMED;
        } else {
            participationState = ParticipationState.PENDING;
        }
        ParticipationRequest participationRequest = RequestMapper.toParticipationRequest(user,
                LocalDateTime.now(), event, participationState);
        log.info("Заявка создана {}", participationRequest);
        return RequestMapper.toParticipationRequestDto(requestRepository.save(participationRequest));
    }

    /**
     * Отмена своего запроса на участие в событии
     *
     * @param userId    - id текущего пользователя
     * @param requestId - id запроса на участие
     * @return - запрос в формате ParticipationRequestDto с измененным статусом на ParticipationState.CANCELED
     */
    @Override
    public ParticipationRequestDto cancelRequest(long userId, long requestId) {
        ParticipationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Запрос не найден или недоступен"));
        request.setStatus(ParticipationState.CANCELED);
        log.info("Заявка отменена {}", request);
        return RequestMapper.toParticipationRequestDto(requestRepository.save(request));
    }

}