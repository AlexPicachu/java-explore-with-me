package ru.practicum.stats.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.dto.stats.EndpointHit;
import ru.practicum.dto.stats.ViewStats;
import ru.practicum.stats.exeption.ValidationException;
import ru.practicum.stats.mapper.Mapper;
import ru.practicum.stats.model.Stats;
import ru.practicum.stats.repository.StateRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * класс, реализующий логику интерфейса StatsService
 */
@Slf4j
@Service
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StateRepository repository;

    /**
     * метод добавления статистики
     *
     * @param endpointHit - принимает на вход Stats в формате EndpointHit
     */
    @Override
    public void add(EndpointHit endpointHit) {
        Stats stats = Mapper.toStats(endpointHit);
        repository.save(stats);
    }

    /**
     * метод возвращающий статистику за определенный промежуток времени
     *
     * @param start  -Дата и время начала диапазона за который нужно выгрузить статистику
     * @param end    - Дата и время конца диапазона за который нужно выгрузить статистику
     * @param uris   - Список uri для которых нужно выгрузить статистику
     * @param unique Нужно ли учитывать только уникальные посещения (только с уникальным ip)
     * @return - статистику за определенный промежуток времени
     */
    @Override
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (start.isAfter(end) || start.equals(end)) {
            throw new ValidationException("Некорректно заданы временные заданы параметры поиска");
        }
        List<ViewStats> stats;
        if (uris == null || uris.isEmpty()) {
            if (unique) {
                stats = repository.getStatsUnique(start, end);
            } else {
                stats = repository.getStats(start, end);
            }
        } else {
            if (!unique) {
                stats = repository.getStatsForUri(start, end, uris);
            } else {
                stats = repository.getStatsForUriUnique(start, end, uris);
            }

        }
        log.info("Возвращаем запрос {}", stats);
        return stats;
    }
}
