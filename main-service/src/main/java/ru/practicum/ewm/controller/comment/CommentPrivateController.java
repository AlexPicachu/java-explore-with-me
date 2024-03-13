package ru.practicum.ewm.controller.comment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.dto.comment.RequestCommentDto;
import ru.practicum.ewm.service.comment.CommentService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users/{userId}/comment")
public class CommentPrivateController {

    private final CommentService commentService;

    /**
     * Добавление нового комментария
     *
     * @param userId            - id пользователя, добавляющего комментарий
     * @param eventId           - id события к которому добавляется комментарий
     * @param requestCommentDto - добавляемый комментарий в формате RequestCommentDto
     * @return - добавленный комментарий в формате CommentDto
     */
    @PostMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(@PathVariable long userId, @PathVariable long eventId,
                                 @RequestBody @Valid RequestCommentDto requestCommentDto) {
        log.info("Данные для добавления комментария {}", requestCommentDto);
        return commentService.addComment(userId, eventId, requestCommentDto);
    }

    /**
     * Обновление комментария. пользователем который его оставил
     *
     * @param userId            - id пользователя
     * @param commentId         - id комментария
     * @param requestCommentDto - обновленный комментарий, который необходимо добавить
     * @return - обновленный комментарий в формате CommentDto
     */
    @PatchMapping("/{commentId}")
    public CommentDto updateComment(@PathVariable long userId, @PathVariable long commentId,
                                    @RequestBody @Valid RequestCommentDto requestCommentDto) {
        log.info("Данные для обновления комментария {}", requestCommentDto);
        return commentService.updateComment(userId, commentId, requestCommentDto);
    }

    /**
     * Удаление комментария
     *
     * @param userId  - id пользователя который хочет удалить свой комментарий
     * @param eventId - id события у которого пользователь хочет удалить комментарий
     */
    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable long userId, @PathVariable long eventId) {
        commentService.deleteComment(userId, eventId);
    }

    /**
     * Просмотр комментариев к определенному событию
     *
     * @param userId  - id пользователя просматривающего комментарии
     * @param eventId - id события у которого просматриваются комментарии
     * @return - список комментариев в формате CommentDto
     */
    @GetMapping("/{eventId}")
    public List<CommentDto> getCommentsByEven(@PathVariable long userId, @PathVariable long eventId) {
        return commentService.getCommentsByEven(userId, eventId);
    }

}
