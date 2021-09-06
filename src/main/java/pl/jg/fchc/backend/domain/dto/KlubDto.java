package pl.jg.fchc.backend.domain.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class KlubDto {

    private Long id;
    @Size(min = 3, max = 100, message
            = "Nazwa musi zawierać od 3 do 100 znaków")
    private String nazwa;
    private List<ZawodnikDto> zawodnicyList;
    @Transient
    private String temp;
}
