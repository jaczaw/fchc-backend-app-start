package pl.jg.fchc.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZawodnikDto {

    private Long id;
    private String nazwaZawodnika;
    private Integer wzrost;
    private LocalDate dataUrodzenia;
}