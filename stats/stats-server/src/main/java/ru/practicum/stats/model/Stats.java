package ru.practicum.stats.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Модель статистики
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "stats")
public class Stats {

    //Идентификатор записи
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Идентификатор сервиса для которого записывается информация
    @Column(name = "app")
    private String app;

    //URI для которого был осуществлен запрос
    @Column(name = "uri")
    private String uri;

    //IP-адрес пользователя, осуществившего запрос
    @Column(name = "ip")
    private String ip;

    //Дата и время, когда был совершен запрос к эндпоинту (в формате \"yyyy-MM-dd HH:mm:ss\")
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

}
