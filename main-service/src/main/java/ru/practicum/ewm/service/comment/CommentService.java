package ru.practicum.ewm.service.comment;

import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.dto.comment.RequestCommentDto;
import ru.practicum.ewm.enums.CommentState;

import java.util.List;

/**
 * контракт для реализации CommentServiceImpl
 */
public interface CommentService {
    /**
     * Добавление нового комментария
     *
     * @param userId            - id пользователя, добавляющего комментарий
     * @param eventId           - id события к которому добавляется комментарий
     * @param requestCommentDto - добавляемый комментарий в формате RequestCommentDto
     * @return - добавленный комментарий в формате CommentDto
     */
    CommentDto addComment(long userId, long eventId, RequestCommentDto requestCommentDto);

    /**
     * Обновление комментария. пользователем который его оставил
     *
     * @param userId            - id пользователя
     * @param commentId         - id комментария
     * @param requestCommentDto - обновленный комментарий, который необходимо добавить
     * @return - обновленный комментарий в формате CommentDto
     */
    CommentDto updateComment(long userId, long commentId, RequestCommentDto requestCommentDto);

    /**
     * Удаление комментария
     *
     * @param userId  - id пользователя который хочет удалить свой комментарий
     * @param eventId - id события у которого пользователь хочет удалить комментарий
     */
    void deleteComment(long userId, long eventId);

    /**
     * Просмотр комментариев к определенному событию
     *
     * @param userId  - id пользователя просматривающего комментарии
     * @param eventId - id события у которого просматриваются комментарии
     * @return - список комментариев в формате CommentDto
     */
    List<CommentDto> getCommentsByEven(long userId, long eventId);

    /**
     * Обновление статуса комментария(опубликован, отклонен)
     *
     * @param commentId    - id комментария
     * @param commentState - статус для обновления
     * @return - обновленный комментарий в формате CommentDto
     */
    CommentDto updateStateComment(long commentId, CommentState commentState);

    /**
     * Запрос списка комментариев в заданном временном интервале
     *
     * @param rangeStart - дата и время не раньше которых должна произойти выборка
     * @param rangeEnd   - дата и время не позже которых должна произойти выборка
     * @param from       - количество комментариев, которые нужно пропустить для формирования текущего набора
     * @param size       - количество комментариев в наборе
     * @return - отсортированный список комментариев в формате CommentDto
     */
    List<CommentDto> getCommentsList(String rangeStart, String rangeEnd, int from, int size);
}
