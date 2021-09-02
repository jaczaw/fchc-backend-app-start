package pl.jg.fchc.backend.domain.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

@Data
@Builder
public class KlubDTO {

    private Long id;
    private String nazwa;
    private List<ZawodnikDTO> zawodnicyList;
    @Transient
    private String temp;
}
