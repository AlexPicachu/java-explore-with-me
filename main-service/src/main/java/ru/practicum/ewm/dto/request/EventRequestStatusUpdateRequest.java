package ru.practicum.ewm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.enums.ParticipationState;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateRequest {
    //Идентификаторы запросов на участие в событии текущего пользователя
    private List<Long> requestIds;

    //Новый статус запроса на участие в событии текущего пользователя
    private ParticipationState status;
}
