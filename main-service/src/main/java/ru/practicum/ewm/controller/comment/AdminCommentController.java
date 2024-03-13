package ru.practicum.ewm.controller.comment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.enums.CommentState;
import ru.practicum.ewm.service.comment.CommentService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("admin/comment")
public class AdminCommentController {

    private final CommentService commentService;

    /**
     * Обновление статуса комментария(опубликован, отклонен)
     *
     * @param commentId    - id комментария
     * @param commentState - статус для обновления
     * @return - обновленный комментарий в формате CommentDto
     */
    @PatchMapping("/{commentId}")
    public CommentDto updateStateComment(@PathVariable long commentId, @RequestParam CommentState commentState) {
        log.info("Параметры для смены статуса {}", commentState);
        return commentService.updateStateComment(commentId, commentState);
    }

    /**
     * Запрос списка комментариев в заданном временном интервале
     *
     * @param rangeStart - дата и время не раньше которых должна произойти выборка
     * @param rangeEnd   - дата и время не позже которых должна произойти выборка
     * @param from       - количество комментариев, которые нужно пропустить для формирования текущего набора
     * @param size       - количество комментариев в наборе
     * @return - отсортированный список комментариев в формате CommentDto
     */
    @GetMapping
    public List<CommentDto> getCommentsList(@RequestParam(required = false) String rangeStart,
                                            @RequestParam(required = false) String rangeEnd,
                                            @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                            @RequestParam(defaultValue = "10") @Positive int size) {
        return commentService.getCommentsList(rangeStart, rangeEnd, from, size);
    }
}
