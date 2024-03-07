package ru.practicum.ewm.dto.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    //широта
    @NotNull
    private Float lat;
    //долгота
    @NotNull
    private Float lon;
}
