package ru.practicum.ewm.mapper.compilations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.dto.compilations.CompilationDto;
import ru.practicum.ewm.dto.compilations.NewCompilationDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.model.compilations.Compilation;
import ru.practicum.ewm.model.event.Event;

import java.util.List;

/**
 * класс Mapper
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompilationMapper {
    /**
     * Метод преобразования NewCompilationDto и List<Event> в Compilation
     */
    public static Compilation toCompilation(NewCompilationDto newCompilationDto, List<Event> events) {
        return Compilation.builder()
                .events(events)
                .pinned(newCompilationDto.getPinned())
                .title(newCompilationDto.getTitle())
                .build();
    }

    /**
     * Метод преобразования Compilation и List<EventShortDto> в CompilationDto
     */
    public static CompilationDto toCompilationDto(Compilation compilation, List<EventShortDto> events) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .events(events)
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }
}
