package ru.practicum.ewm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Результат подтверждения/отклонения заявок на участие в событии
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateResult {
    //подтвержденные запросы
    private List<ParticipationRequestDto> confirmedRequests;
    //отклоненные запросы
    private List<ParticipationRequestDto> rejectedRequests;
}
