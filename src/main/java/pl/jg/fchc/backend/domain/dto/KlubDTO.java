package pl.jg.fchc.backend.domain.dto;

import lombok.Data;

import javax.persistence.Transient;

@Data
public class KlubDTO {

    private Long id;
    private String nazwa;
    @Transient
    private String temp;
}
