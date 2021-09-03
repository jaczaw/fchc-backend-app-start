package pl.jg.fchc.backend.domain.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

@Data
@Builder
public class KlubDto {

    private Long id;
    private String nazwa;
    private List<ZawodnikDto> zawodnicyList;
    @Transient
    private String temp;
}
