package pl.jg.fchc.backend.domain.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Transient;

@Data
@Builder
public class KlubViewDTO {

    private Long id;
    private String nazwa;
    @Transient
    private String temp;
}
