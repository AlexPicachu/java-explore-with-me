package ru.practicum.ewm.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.model.location.Location;

/**
 * контракт для реализации JpaRepository
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
