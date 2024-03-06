package ru.practicum.ewm.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.model.user.User;

/**
 * контракт для реализации JpaRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
