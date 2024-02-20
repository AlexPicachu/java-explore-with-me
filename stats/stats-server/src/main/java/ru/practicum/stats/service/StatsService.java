package ru.practicum.stats.service;

import ru.practicum.dto.stats.EndpointHit;
import ru.practicum.dto.stats.ViewStats;

import java.util.List;

/**
 * контракт для реализации класса StatsServiceImpl
 */
public interface StatsService {

    /**
     * метод добавления статистики
     *
     * @param endpointHit - принимает на вход Stats в формате EndpointHit
     */
    void add(EndpointHit endpointHit);

    /**
     * метод возвращающий статистику за определенный промежуток времени
     *
     * @param start  -Дата и время начала диапазона за который нужно выгрузить статистику
     * @param end    - Дата и время конца диапазона за который нужно выгрузить статистику
     * @param uris   - Список uri для которых нужно выгрузить статистику
     * @param unique Нужно ли учитывать только уникальные посещения (только с уникальным ip)
     * @return
     */
    List<ViewStats> getStats(String start, String end, List<String> uris, Boolean unique);

}
